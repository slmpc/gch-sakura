package dev.exceptionteam.addon.utils

import dev.exceptionteam.sakura.translation.TranslationKey

object LanguageUtils {

    fun getLanguageData(language: String): Map<TranslationKey, String> {
        val res = AddonResource("lang/$language.lang")
        val data = res.data

        val translations = mutableMapOf<TranslationKey, String>()

        data.lines().forEach { line ->
            if (line.startsWith("#")) {
                return@forEach
            } else {
                val key = line.substringBefore("=")
                val value = line.substringAfter("=")
                translations[TranslationKey(key)] = value
            }
        }

        return translations
    }

}