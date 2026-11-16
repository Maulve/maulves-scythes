package maulve.scythes.item;

//? >= 1.21.4 {

import maulve.scythes.platform.fabric.datagen.ModItemTagProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {
	public static final ToolMaterial AMETHYST = new ToolMaterial(
			AmethystMaterialDefinition.INCORRECT,
			AmethystMaterialDefinition.DURABILITY,
			AmethystMaterialDefinition.MINING_SPEED,
			AmethystMaterialDefinition.ATTACK_DAMAGE,
			AmethystMaterialDefinition.ENCHANTABILITY,
			ModItemTags.AMETHYST_REPAIR
	);
}

//? }
