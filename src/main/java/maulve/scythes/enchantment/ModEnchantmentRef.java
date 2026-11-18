package maulve.scythes.enchantment;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentRef {
	private final ResourceKey<Enchantment> key;

	//? neoforge && <= 1.20.4 {
	/*public ModEnchantmentRef(ResourceKey<Enchantment> key) {
		this.key = key;
	}

	public Holder.Reference<Enchantment> get(RegistryAccess registryAccess) {
		HolderLookup.RegistryLookup<Enchantment> registry = registryAccess.lookupOrThrow(Registries.ENCHANTMENT);
		return registry.getOrThrow(key);
	}
	*///? } fabric && <= 1.20.4 {
	/*private final Enchantment instance;

	public ModEnchantmentRef(ResourceKey<Enchantment> key, Enchantment instance) {
		this.key = key;
		this.instance = instance;
	}

	public Enchantment get() {
		return instance;
	}

	*///? } fabric && >= 1.21.1 {
	public ModEnchantmentRef(ResourceKey<Enchantment> key) {
		this.key = key;
	}

	public Holder.Reference<Enchantment> get(RegistryAccess registryAccess) {
		//? if >= 1.21.4 {
		Registry<Enchantment> registry = registryAccess.lookupOrThrow(Registries.ENCHANTMENT);
		return registry.getOrThrow(key);
		//? } else {
		/*return registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(key);
		 *///? }
	}
	//? }
}
