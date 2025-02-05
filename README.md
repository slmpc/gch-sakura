# Sakura Addon Template

This is a template for creating a Sakura Addon.

**You should put the latest `Sakura.jar` & `Sakura-sources.jar` in the `libs` folder.**

Project Structure:
```
├───gradle
│   └───wrapper
│           gradle-wrapper.jar
│           gradle-wrapper.properties
│
├───libs
│       Sakura.jar
│
├───src
│   └───main
│       ├───java
│       │   └───dev
│       │       └───exceptionteam
│       │           └───addon
│       │               └───mixins
│       │                       MinecraftMixin.java
│       │
│       ├───kotlin
│       │   └───dev
│       │       └───exceptionteam
│       │           └───addon
│       │                   Addon.kt
│       │
│       └───resources
│               addon.accesswidener
│               addon.mixins.json
│               fabric.mod.json
│   .gitignore
│   build.gradle.kts
│   gradle.properties
│   gradlew
│   gradlew.bat
│   LICENSE.txt
│   README.md
│   settings.gradle.kts
```
