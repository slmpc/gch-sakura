package dev.exceptionteam.addon.utils

import dev.exceptionteam.addon.Addon

class AddonResource(
    path: String
) {
    val byteArr: ByteArray

    val data: String get() = String(byteArr, Charsets.UTF_8)

    init {
        val stream = javaClass.getResourceAsStream("${Addon.ASSETS_DIRECTORY}/${path}")
            ?: throw IllegalArgumentException("Resource not found: ${Addon.ASSETS_DIRECTORY}/${path}")

        byteArr = stream.readBytes()

        stream.close()
    }

}
