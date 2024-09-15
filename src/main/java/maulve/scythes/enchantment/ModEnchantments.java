package maulve.scythes.enchantment;

import com.mojang.serialization.MapCodec;
import maulve.scythes.MaulvesScythes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> LIFE_DRAIN = of("life_drain");

    private static RegistryKey<Enchantment> of(final String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MaulvesScythes.MOD_ID, name));
    }

    private static void registerEntityEffect(final Identifier identifier, final MapCodec<? extends EnchantmentEntityEffect> codec) {
        Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, identifier, codec);
    }

    public static RegistryEntry<Enchantment> getEntry(DynamicRegistryManager registryManager, RegistryKey<Enchantment> key) {
        Registry<Enchantment> registry = registryManager.get(RegistryKeys.ENCHANTMENT);
        return registry.getEntry(key).orElseThrow();
    }

    public static Enchantment getEnchantment(RegistryEntry<Enchantment> entry) {
        return entry.value();
    }

    public static void register() {
        registerEntityEffect(HealEnchantmentEffect.IDENTIFIER, HealEnchantmentEffect.CODEC);
    }
}
