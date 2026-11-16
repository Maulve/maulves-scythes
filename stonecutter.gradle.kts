plugins {
	id("dev.kikugie.stonecutter")

	// Declared once here so every node's build script can apply them by id, without a version.
	id("net.fabricmc.fabric-loom-remap") version "1.19.4" apply false   // all targets are <= 1.21.11 (obfuscated)
	id("net.neoforged.moddev") version "2.0.148" apply false
}

stonecutter active "1.21.11-fabric" /* [SC] DO NOT EDIT */

// Makes `//? if fabric { ... }` and `//? if neoforge { ... }` work in source files.
stonecutter parameters {
	val loader = node.project.name.substringAfterLast('-')
	constants["fabric"] = loader == "fabric"
	constants["neoforge"] = loader == "neoforge"

	swaps["mod_id"] = "\"${node.project.property("mod.id")}\";"
	swaps["mod_version"] = "\"${node.project.property("mod.version")}\";"
	swaps["mod_name"] = "\"${node.project.property("mod.name")}\";"

	replacements {
		string(current.parsed < "1.21.11") {
			replace("Identifier", "ResourceLocation")
			replace("net.minecraft.advancements.criterion.", "net.minecraft.advancements.critereon.")
			replace("VegetationBlock", "BushBlock")
			replace("valueLookupBuilder(", "getOrCreateTagBuilder(")
			replace("ItemTags.MELEE_WEAPON_ENCHANTABLE", "ItemTags.SWORD_ENCHANTABLE")
			replace(".identifier()", ".location()")
		}
		string(current.parsed < "1.21.4") {
			//replace("net.fabricmc.fabric.api.client.datagen.v1.provider", "net.fabricmc.fabric.api.datagen.v1.provider")
			replace("net.minecraft.client.data.models", "net.minecraft.data.models")
		}
	}
}
