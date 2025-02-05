package dev.exceptionteam.addon

import dev.exceptionteam.addon.module.client.ExampleModule
import dev.exceptionteam.addon.utils.LanguageUtils
import dev.exceptionteam.sakura.Sakura
import dev.exceptionteam.sakura.addons.SakuraAddon
import dev.exceptionteam.sakura.features.modules.AbstractModule
import dev.exceptionteam.sakura.translation.TranslationKey

object Addon: SakuraAddon() {
    const val ASSETS_DIRECTORY = "/assets/addon"

    override fun getLanguageData(): Map<String, Map<TranslationKey, String>> {
        return mapOf(
            Pair("en_us", LanguageUtils.getLanguageData("en_us")),
            Pair("zh_cn", LanguageUtils.getLanguageData("zh_cn"))
        )
    }

    override fun onInitialize() {
        Sakura.logger.warn("Addon is enabled!")
    }

    override fun getModules(): List<AbstractModule> =
        listOf<AbstractModule>(
            ExampleModule
        )
}