package dev.exceptionteam.addon.module.render

import dev.exceptionteam.sakura.events.impl.Render3DEvent
import dev.exceptionteam.sakura.events.impl.TickEvents
import dev.exceptionteam.sakura.events.nonNullListener
import dev.exceptionteam.sakura.features.modules.Category
import dev.exceptionteam.sakura.features.modules.Module
import dev.exceptionteam.sakura.graphics.color.ColorRGB
import dev.exceptionteam.sakura.graphics.general.ESPRenderer
import dev.exceptionteam.sakura.utils.world.WorldUtils.blockState
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks

object KelpESP: Module(
    name = "kelp-esp",
    category = Category.RENDER,
) {

    private val range by setting("range", 32, 1..64)

    private val kelpList = mutableListOf<BlockPos>()
    private val renderer = ESPRenderer().apply { aFilled = 70 }

    init {
        nonNullListener<TickEvents.Update> {
            synchronized(kelpList) {
                kelpList.clear()

                player
                    .blockPosition()
                    .aroundBlockHeight(range, 20)
                    .filter { it.blockState?.block == Blocks.KELP || it.blockState?.block == Blocks.KELP_PLANT }
                    .forEach { kelpList.add(it) }
            }
        }

        nonNullListener<Render3DEvent> {
            synchronized(kelpList) {
                kelpList.forEach {
                    renderer.add(it, ColorRGB(255, 255, 0))
                }
                renderer.render(true)
            }
        }
    }

    fun BlockPos.aroundBlockHeight(range: Int, height: Int): List<BlockPos> {
        val result = mutableListOf<BlockPos>()

        for (x in -range..range) {
            for (y in -height..height) {
                for (z in -range..range) {
                    result.add(this.offset(x, y, z))
                }
            }
        }

        return result
    }


}