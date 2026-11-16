package maulve.scythes.item;

import net.minecraft.world.item.Item;
//? if >= 1.21.4 {
import net.minecraft.world.item.ToolMaterial;
//? } else {
/*import net.minecraft.world.item.Tier;
*///? }

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
			MaterialHelper.get(MaterialHelper.Key.IRON),
			ATTACK_DAMAGE,
			ATTACK_SPEED - 0.1F
	);

	public static final ItemBlueprint<ScytheItem> DIAMOND_SCYTHE = registerScythe(
			"diamond_scythe",
			MaterialHelper.get(MaterialHelper.Key.DIAMOND),
			ATTACK_DAMAGE,
			ATTACK_SPEED
	);

	public static final ItemBlueprint<ScytheItem> NETHERITE_SCYTHE = registerScythe(
			"netherite_scythe",
			MaterialHelper.get(MaterialHelper.Key.NETHERITE),
			ATTACK_DAMAGE,
			ATTACK_SPEED
	);

	public static final ItemBlueprint<AmethystScytheItem> AMETHYST_SCYTHE = registerAmethystScythe(
			"amethyst_scythe",
			MaterialHelper.get(MaterialHelper.Key.AMETHYST),
			ATTACK_DAMAGE,
			ATTACK_SPEED
	);

	//? if >= 1.21.4 {
	private static ItemBlueprint<AmethystScytheItem> registerAmethystScythe(String name, ToolMaterial toolMaterial, float attackDamage, float attackSpeed) {
		//? } else {
	/*private static ItemBlueprint<AmethystScytheItem> registerAmethystScythe(String name, Tier toolMaterial, float attackDamage, float attackSpeed) {
		*///? }
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

	//? if >= 1.21.4 {
	private static ItemBlueprint<ScytheItem> registerScythe(String name, ToolMaterial toolMaterial, float attackDamage, float attackSpeed) {
		//? } else {
	/*private static ItemBlueprint<ScytheItem> registerScythe(String name, Tier toolMaterial, float attackDamage, float attackSpeed) {
		*///? }
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
