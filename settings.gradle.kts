pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		maven("https://maven.fabricmc.net/")
		maven("https://maven.neoforged.net/releases/")
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.9.8"
}

stonecutter {
	create(rootProject) {
		fun target(mc: String, vararg loaders: String) =
			loaders.forEach { version("$mc-$it", mc).buildscript("build.$it.gradle.kts") }

		target("1.20.4", "fabric", "neoforge")
		target("1.21.1", "fabric", "neoforge")
		target("1.21.4", "fabric", "neoforge")
		target("1.21.11", "fabric", "neoforge")

		vcsVersion = "1.21.11-fabric"
	}
}

rootProject.name = "maulves-scythes"
