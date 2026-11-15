plugins {
	id("net.fabricmc.fabric-loom-remap")
}

fun prop(key: String) = property(key) as String

val mc = stonecutter.current.version
val javaVersion = prop("java.$mc").toInt()

group = prop("mod.group")
version = "${prop("mod.version")}+$mc"
base.archivesName.set("${prop("mod.id")}-fabric")

repositories {
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://maven.nucleoid.xyz/")
}

dependencies {
	minecraft("com.mojang:minecraft:$mc")
	mappings(loom.officialMojangMappings())

	modImplementation("net.fabricmc:fabric-loader:${prop("fabric.loader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${prop("fabric.api.$mc")}")
	modImplementation("com.terraformersmc:modmenu:${prop("fabric.modmenu.$mc")}")
}

loom {
	runConfigs.configureEach {
		runDir = "../../run" // one shared run directory for every node
	}
}

fabricApi {
	configureDataGeneration {
		client = true
	}
}

java {
	withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
	options.release = javaVersion
}

tasks.processResources {
	val props = mapOf(
		"id" to prop("mod.id"),
		"name" to prop("mod.name"),
		"version" to project.version.toString(),
		"description" to prop("mod.description"),
		"license" to prop("mod.license"),
		"authors" to prop("mod.authors"),
		"main_package" to prop("mod.main_package"),
		"minecraft_range" to prop("fabric.mc.$mc"),
		"java_version" to javaVersion.toString(),
		"JAVA_VERSION" to javaVersion.toString(),
	)
	inputs.properties(props)

	filesMatching("fabric.mod.json") { expand(props) }
	filesMatching("*.mixins.json") { expand(props) }
	exclude("META-INF/neoforge.mods.toml")
}
