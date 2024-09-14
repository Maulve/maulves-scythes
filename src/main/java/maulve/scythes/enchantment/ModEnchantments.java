package maulve.scythes.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    private static void registerEntityEffect(final Identifier identifier, final MapCodec<? extends EnchantmentEntityEffect> codec) {
        Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, identifier, codec);
    }

    public static void register() {
        registerEntityEffect(HealEnchantmentEffect.IDENTIFIER, HealEnchantmentEffect.CODEC);
    }
}
