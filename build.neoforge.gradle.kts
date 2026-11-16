plugins {
	id("net.neoforged.moddev")
}

fun prop(key: String) = property(key) as String

val mc = stonecutter.current.version
val javaVersion = prop("java.$mc").toInt()

// NeoForge renamed META-INF/mods.toml -> META-INF/neoforge.mods.toml and changed the
// dependency syntax in 1.20.5. Everything older than that gets the legacy form.
val legacyMetadata = stonecutter.eval(mc, "<1.20.5")

group = prop("mod.group")
version = "${prop("mod.version")}+$mc"
base.archivesName.set("${prop("mod.id")}-neoforge")

neoForge {
	version = prop("neoforge.version.$mc")

	runs {
		register("client") {
			client()
			gameDirectory.set(rootProject.layout.projectDirectory.dir("run")) // shared by every node
		}
		register("server") {
			server()
			gameDirectory.set(rootProject.layout.projectDirectory.dir("run-server"))
		}
	}

	mods {
		register(prop("mod.id")) {
			sourceSet(sourceSets.main.get())
		}
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
		"main_package" to prop("mod.main_package"),
		"id" to prop("mod.id"),
		"name" to prop("mod.name"),
		"version" to project.version.toString(),
		"description" to prop("mod.description"),
		"license" to prop("mod.license"),
		"authors" to prop("mod.authors"),
		"minecraft_range" to prop("neoforge.mc.$mc"),
		"neoforge_range" to prop("neoforge.range.$mc"),
		"loader_version" to if (legacyMetadata) "[1,)" else "[4,)",
		"java_version" to prop("java.$mc")
		// modern neoforge toolchains don't require the split anymore
		//"dependency_type" to if (legacyMetadata) "mandatory = true" else "type = \"required\"",
	)
	inputs.properties(props)

	filesMatching("META-INF/neoforge.mods.toml") { expand(props) }
	filesMatching( "*.mixins.json") { expand(props) }
	exclude("fabric.mod.json")
	if (legacyMetadata) rename("neoforge.mods.toml", "mods.toml")
}
