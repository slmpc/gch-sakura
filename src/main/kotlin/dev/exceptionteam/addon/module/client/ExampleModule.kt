package dev.exceptionteam.addon.module.client

import dev.exceptionteam.sakura.features.modules.Category
import dev.exceptionteam.sakura.features.modules.Module
import dev.exceptionteam.sakura.utils.interfaces.TranslationEnum

object ExampleModule: Module(
    name = "example-module",
    category = Category.CLIENT
) {

    private val page by setting("page", Page.GENERAL)

    private val slider by setting("slider", 200, 100..1000) { page == Page.GENERAL }

    private enum class Page(override val key: CharSequence): TranslationEnum {
        GENERAL("general"),
        TEST("test")
    }

}