package maulve.scythes.enchantment;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.util.ScythesEnchantmentHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
//? neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
*///? }
//? >= 1.21.1 {
import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.ModItemTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

import static maulve.scythes.util.ScythesEnchantmentHelper.key;
//? }

public class ModEnchantments {
	//? neoforge && <= 1.20.4 {
	/*private static final DeferredRegister<Enchantment> ENCHANTMENTS =
			DeferredRegister.create(Registries.ENCHANTMENT, MaulvesScythes.MOD_ID);
	public static final ModEnchantmentRef LIFE_DRAIN = ScythesEnchantmentHelper.register(ENCHANTMENTS, ScythesEnchantmentHelper.LIFE_DRAIN);
	public static final ModEnchantmentRef SWEEPING_ARC = ScythesEnchantmentHelper.register(ENCHANTMENTS, ScythesEnchantmentHelper.SWEEPING_ARC);

	public static void initialize(IEventBus modEventBus) {
		ENCHANTMENTS.register(modEventBus);
	}
	*///? } fabric && <= 1.20.4 {
	/*public static final ModEnchantmentRef LIFE_DRAIN = ScythesEnchantmentHelper.register(ScythesEnchantmentHelper.LIFE_DRAIN);
	public static final ModEnchantmentRef SWEEPING_ARC = ScythesEnchantmentHelper.register(ScythesEnchantmentHelper.SWEEPING_ARC);

	public static void initialize() {}
	*///? } fabric && >= 1.21.1 {
	public static final ModEnchantmentRef LIFE_DRAIN = new ModEnchantmentRef(ScythesEnchantmentHelper.LIFE_DRAIN.key());
	public static final ModEnchantmentRef SWEEPING_ARC = new ModEnchantmentRef(ScythesEnchantmentHelper.SWEEPING_ARC.key());

	public static void bootstrap(BootstrapContext<Enchantment> context) {
		HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
		HolderGetter<Item> items = context.lookup(Registries.ITEM);

		register(context, enchantments, items, ScythesEnchantmentHelper.LIFE_DRAIN);
		register(context, enchantments, items, ScythesEnchantmentHelper.SWEEPING_ARC);
	}

	public static void register(BootstrapContext context, HolderGetter<Enchantment> enchantments, HolderGetter<Item> items, ScythesEnchantmentHelper.Definition definition) {
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

		ResourceKey<Enchantment> key = key(definition.id());

		context.register(key, builder.build(key.identifier()));
	}
	//? }
}
