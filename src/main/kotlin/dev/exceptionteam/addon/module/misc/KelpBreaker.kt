package dev.exceptionteam.addon.module.misc

import dev.exceptionteam.sakura.events.impl.Render3DEvent
import dev.exceptionteam.sakura.events.impl.TickEvents
import dev.exceptionteam.sakura.events.nonNullListener
import dev.exceptionteam.sakura.features.modules.Category
import dev.exceptionteam.sakura.features.modules.Module
import dev.exceptionteam.sakura.graphics.color.ColorRGB
import dev.exceptionteam.sakura.graphics.general.ESPRenderer
import dev.exceptionteam.sakura.managers.impl.RotationManager.addRotation
import dev.exceptionteam.sakura.utils.math.RotationUtils.getRotationTo
import dev.exceptionteam.sakura.utils.world.WorldUtils.blockState
import dev.exceptionteam.sakura.utils.world.aroundBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.level.block.Blocks

object KelpBreaker: Module(
    name = "kelp-breaker",
    category = Category.MISC
) {

    private val range by setting("range", 5, 1..10)

    private val renderer = ESPRenderer().apply { aFilled = 70 }
    private var lastBreakPos: BlockPos? = null

    init {

        nonNullListener<TickEvents.Update> {

            lastBreakPos = null

            player
                .blockPosition()
                .aroundBlock(range)
                .filter { it.blockState?.block == Blocks.KELP || it.blockState?.block == Blocks.KELP_PLANT }
                .filter { it.below().blockState?.block != Blocks.KELP && it.below().blockState?.block != Blocks.KELP_PLANT }
                .let { blocks ->
                    val blockPos = blocks.firstOrNull() ?: return@nonNullListener

                    val angle = getRotationTo(blockPos, Direction.UP)
                    addRotation(angle, 0) {
                        connection.send(
                            ServerboundPlayerActionPacket(
                                ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK,
                                blockPos, Direction.UP, 0
                            )
                        )

                        connection.send(
                            ServerboundPlayerActionPacket(
                                ServerboundPlayerActionPacket.Action.STOP_DESTROY_BLOCK,
                                blockPos, Direction.UP, 0
                            )
                        )

                        lastBreakPos = blockPos

                        player.swing(InteractionHand.MAIN_HAND)
                    }
                }

        }

        nonNullListener<Render3DEvent> {
            lastBreakPos?.let {
                renderer.add(it, ColorRGB(255, 0, 0))
                renderer.render(true)
            }
        }

    }

}