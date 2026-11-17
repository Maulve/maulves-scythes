package maulve.scythes.util;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.enchantment.ModEnchantmentRef;
import maulve.scythes.enchantment.ScytheEnchantment;
import maulve.scythes.item.ModItemTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
//? >= 1.21.1
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.List;

public class ScythesEnchantmentHelper {
	public record CostCurve(int base, int perLevel) {
		public int calculate(int level) {
			return base + (level - 1) * perLevel;
		}
		//? >= 1.21.1 {
		public Enchantment.Cost toMinecraftCost() {
			return Enchantment.dynamicCost(base, perLevel);
		}
		//? }
	}

	public record Definition (
		String id,
		TagKey<Item> supportedItems,
		int weight,
		int maxLevel,
		CostCurve minCost,
		CostCurve maxCost,
		int anvilCost,
		List<Identifier> exclusiveIds
		) {
		public ResourceKey<Enchantment> key() {
			return ScythesEnchantmentHelper.key(id);
		}
	}

	public static final Definition LIFE_DRAIN = new Definition(
			"life_drain",
			ModItemTags.SCYTHES,
			2,
			1,
			new CostCurve(10, 10),
			new CostCurve(50, 20),
			4,
			List.of(
					MaulvesScythes.id("minecraft", "looting"),
					MaulvesScythes.id("minecraft", "sweeping_edge")
			)
	);

	public static final Definition SWEEPING_ARC = new Definition(
			"sweeping_arc",
			ModItemTags.SCYTHES,
			2,
			1,
			new CostCurve(10, 10),
			new CostCurve(50, 20),
			4,
			List.of(
					MaulvesScythes.id("minecraft", "sweeping_edge"),
					MaulvesScythes.id("minecraft", "fire_aspect")
			)
	);

	//? if < 1.21.1 {
	/*public static ModEnchantmentRef register(Definition definition) {
		Enchantment instance = Registry.register(
				BuiltInRegistries.ENCHANTMENT,
				MaulvesScythes.id(definition.id()),
				new ScytheEnchantment(definition)
		);
		return new ModEnchantmentRef(key(definition.id()), instance);
	}
	*///? } else {
	public static ModEnchantmentRef register(BootstrapContext context, Definition definition) {
		var enchantments = context.lookup(Registries.ENCHANTMENT);
		var items = context.lookup(Registries.ITEM);

		List<Holder<Enchantment>> exclusiveList = definition.exclusiveIds().stream()
				.map(id -> (Holder<Enchantment>) enchantments.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, id)))
				.toList();

		HolderSet<Enchantment> exclusives = HolderSet.direct(exclusiveList);

		Enchantment.Builder builder = Enchantment.enchantment(Enchantment.definition(
				items.getOrThrow(definition.supportedItems()),
				definition.weight(),
				definition.maxLevel(),
				definition.minCost().toMinecraftCost(),
				definition.maxCost().toMinecraftCost(),
				definition.anvilCost(),
				EquipmentSlotGroup.MAINHAND)).exclusiveWith(exclusives);

		ResourceKey<Enchantment> key = key(definition.id);

		context.register(key, builder.build(key.identifier()));

		return new ModEnchantmentRef(key);
	}
	//? }

	public static int getLevel(ModEnchantmentRef ref, ItemStack stack) {
		return EnchantmentHelper.getItemEnchantmentLevel(ref.get(), stack);
	}

	public static boolean has(ModEnchantmentRef ref, ItemStack stack) {
		return getLevel(ref, stack) > 0;
	}

	public static ResourceKey<Enchantment> key(final String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, MaulvesScythes.id(name));
	}
}
