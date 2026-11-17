package maulve.scythes.item;

//? >= 1.21.4 {
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
