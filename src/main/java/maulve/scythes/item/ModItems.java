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

	public static final ItemBlueprint<ScytheItem> IRON_SCYTHE = register(
			"iron_scythe",
			ScytheItem::new,
			properties -> new Item.Properties().sword(ToolMaterial.IRON, ATTACK_DAMAGE, ATTACK_SPEED - 0.1F)
	);

	public static final ItemBlueprint<ScytheItem> DIAMOND_SCYTHE = register(
			"diamond_scythe",
			ScytheItem::new,
			properties -> new Item.Properties().sword(ToolMaterial.DIAMOND, ATTACK_DAMAGE, ATTACK_SPEED)
	);

	public static final ItemBlueprint<ScytheItem> NETHERITE_SCYTHE = register(
			"netherite_scythe",
			ScytheItem::new,
			properties -> new Item.Properties().sword(ToolMaterial.NETHERITE, ATTACK_DAMAGE, ATTACK_SPEED)
	);

	public static final ItemBlueprint<AmethystScytheItem> AMETHYST_SCYTHE = register(
			"amethyst_scythe",
			AmethystScytheItem::new,
			properties -> new Item.Properties().sword(ModToolMaterials.AMETHYST, ATTACK_DAMAGE, ATTACK_SPEED)
	);

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
