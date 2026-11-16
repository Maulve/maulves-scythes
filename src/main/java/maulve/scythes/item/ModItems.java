package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModItems {
	private static final List<ItemBlueprint<?>> BLUEPRINTS = new ArrayList<>();

	private static final int ATTACK_DAMAGE = 4;
	private static final float ATTACK_SPEED = -2.8F;

	public static final ItemBlueprint<Item> REINFORCED_STICK = register(
			"reinforced_stick",
			Item::new,
			properties -> new Item.Properties()
	);

	public static final ItemBlueprint<Item> SCYTHED_AMETHYST = register(
			"scythed_amethyst",
			Item::new,
			properties -> new Item.Properties()
	);

	public static final ItemBlueprint<ScytheItem> IRON_SCYTHE = registerScythe(
			"iron_scythe",
			ToolMaterial.IRON,
			ATTACK_DAMAGE,
			ATTACK_SPEED - 0.1F
	);

	public static final ItemBlueprint<ScytheItem> DIAMOND_SCYTHE = registerScythe(
			"diamond_scythe",
			ToolMaterial.DIAMOND,
			ATTACK_DAMAGE,
			ATTACK_SPEED
	);

	public static final ItemBlueprint<ScytheItem> NETHERITE_SCYTHE = registerScythe(
			"netherite_scythe",
			ToolMaterial.NETHERITE,
			ATTACK_DAMAGE,
			ATTACK_SPEED
	);

	public static final ItemBlueprint<AmethystScytheItem> AMETHYST_SCYTHE = registerAmethystScythe(
			"amethyst_scythe",
			ModToolMaterials.AMETHYST,
			ATTACK_DAMAGE,
			ATTACK_SPEED
	);

	private static ItemBlueprint<AmethystScytheItem> registerAmethystScythe(String name, ToolMaterial toolMaterial, float attackDamage, float attackSpeed) {
		return register(
				name,
				//? if >= 1.21.11 {
				AmethystScytheItem::new,
				 props -> new Item.Properties().sword(toolMaterial, attackDamage, attackSpeed)
				//? } else {
				/*props -> new AmethystScytheItem(toolMaterial, attackDamage, attackSpeed, props),
				props -> new Item.Properties()
				*///? }
		);
	}

	private static ItemBlueprint<ScytheItem> registerScythe(String name, ToolMaterial toolMaterial, float attackDamage, float attackSpeed) {
		return register(
				name,
				//? if >= 1.21.11 {
				ScytheItem::new,
				 props -> new Item.Properties().sword(toolMaterial, attackDamage, attackSpeed)
				//? } else {
				/*props -> new ScytheItem(toolMaterial, attackDamage, attackSpeed, props),
				props -> new Item.Properties()
				*///? }
		);
	}

	private static <I extends Item> ItemBlueprint<I> register(
			String name,
			Function<Item.Properties, I> factory,
			UnaryOperator<Item.Properties> modifier) {
		ItemBlueprint<I> blueprint = new ItemBlueprint<>(name, factory, modifier);
		BLUEPRINTS.add(blueprint);
		return blueprint;
	}

	public static List<ItemBlueprint<?>> getBlueprints() {
		return Collections.unmodifiableList(BLUEPRINTS);
	}
}
