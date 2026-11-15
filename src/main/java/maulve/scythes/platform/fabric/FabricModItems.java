package maulve.scythes.platform.fabric;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.AmethystScytheItem;
import maulve.scythes.item.ModToolMaterials;
import maulve.scythes.item.ScytheItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class FabricModItems {
	private static final int ATTACK_DAMAGE = 4;
	private static final float ATTACK_SPEED = -2.8F;

	public static final Item IRON_SCYTHE = registerScythe("iron_scythe",
			ToolMaterial.IRON, ATTACK_DAMAGE, ATTACK_SPEED - 0.1F);

	public static final Item DIAMOND_SCYTHE = registerScythe("diamond_scythe",
			ToolMaterial.DIAMOND, ATTACK_DAMAGE, ATTACK_SPEED);

	public static final Item NETHERITE_SCYTHE = registerScythe("netherite_scythe",
			ToolMaterial.NETHERITE, ATTACK_DAMAGE, ATTACK_SPEED);

	public static final Item AMETHYST_SCYTHE = registerAmethystScythe("amethyst_scythe",
			ModToolMaterials.AMETHYST_MATERIAL, ATTACK_DAMAGE, ATTACK_SPEED);

	private static Item registerItem(String itemName, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, MaulvesScythes.id(itemName), item);
	}

	private static Item registerScythe(String itemName, ToolMaterial toolMaterial, int attackDamage, float attackSpeed) {
		ScytheItem scytheItem = new ScytheItem(new Item.Properties()
				.setId(itemResourceKey(itemName))
				.sword(toolMaterial, attackDamage, attackSpeed));

		return registerItem(itemName, scytheItem);
	}

	private static Item registerAmethystScythe(String itemName, ToolMaterial toolMaterial, int attackDamage, float attackSpeed) {
		AmethystScytheItem amethystScytheItem = new AmethystScytheItem(new Item.Properties()
				.setId(itemResourceKey(itemName))
				.sword(toolMaterial, attackDamage, attackSpeed));

		return registerItem(itemName, amethystScytheItem);
	}

	private static ResourceKey<Item> itemResourceKey(String itemName) {
		return ResourceKey.create(Registries.ITEM, MaulvesScythes.id(itemName));
	}

	public static void initialize() {
		MaulvesScythes.LOGGER.info("Initializing fabric items.");
	}
}
