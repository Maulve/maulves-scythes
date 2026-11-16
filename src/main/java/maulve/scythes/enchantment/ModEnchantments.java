package maulve.scythes.enchantment;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.ModItemTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class ModEnchantments {
	public static final ResourceKey<Enchantment> LIFE_DRAIN = of("life_drain");
	public static final ResourceKey<Enchantment> SWEEPING_ARC = of("sweeping_arc");

	public static void bootstrap(BootstrapContext context) {
		var enchantments = context.lookup(Registries.ENCHANTMENT);
		var items = context.lookup(Registries.ITEM);

		final HolderSet<Enchantment> EXCLUSIVE_SET_LIFE_DRAIN = HolderSet.direct(
				enchantments.getOrThrow(Enchantments.LOOTING),
				enchantments.getOrThrow(Enchantments.SWEEPING_EDGE)
		);
		final HolderSet<Enchantment> EXCLUSIVE_SET_SWEEPING_ARC = HolderSet.direct(
				enchantments.getOrThrow(Enchantments.SWEEPING_EDGE),
				enchantments.getOrThrow(Enchantments.FIRE_ASPECT)
		);

		register(context, LIFE_DRAIN, Enchantment.enchantment(Enchantment.definition(
				items.getOrThrow(ModItemTags.SCYTHES),
				items.getOrThrow(ModItemTags.SCYTHES),
				2,
				1,
				Enchantment.dynamicCost(10, 10),
				Enchantment.dynamicCost(50, 20),
				4,
				EquipmentSlotGroup.MAINHAND)).exclusiveWith(EXCLUSIVE_SET_LIFE_DRAIN));

		register(context, SWEEPING_ARC, Enchantment.enchantment(Enchantment.definition(
				items.getOrThrow(ModItemTags.SCYTHES),
				items.getOrThrow(ModItemTags.SCYTHES),
				2,
				1,
				Enchantment.dynamicCost(10, 10),
				Enchantment.dynamicCost(50, 20),
				4,
				EquipmentSlotGroup.MAINHAND)).exclusiveWith(EXCLUSIVE_SET_SWEEPING_ARC));
	}

	private static void register(BootstrapContext context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
		context.register(key, builder.build(key.identifier()));
	}

	public static Holder.Reference<Enchantment> getEntry(RegistryAccess registryAccess, ResourceKey<Enchantment> key) {
		//? if >= 1.21.4 {
		Registry<Enchantment> registry = registryAccess.lookupOrThrow(Registries.ENCHANTMENT);
		return registry.getOrThrow(key);
		//? } else {
		/*return registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(key);
		*///? }
	}

	private static ResourceKey<Enchantment> of(final String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, MaulvesScythes.id(name));
	}
}
