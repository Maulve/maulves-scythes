package maulve.scythes.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {
	public static final ToolMaterial AMETHYST_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_WOODEN_TOOL,
			2521, // durability
			9.0F, // mining speed
			5.0F, // attack damage
			26, // enchantability
			ItemTags.DIAMOND_TOOL_MATERIALS // repair material (TODO: add scythed amethyst)
	);
}
