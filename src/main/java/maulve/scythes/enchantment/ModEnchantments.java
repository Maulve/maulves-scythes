package maulve.scythes.enchantment;

import maulve.scythes.MaulvesScythes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment LIFE_DRAIN = registerEnchantment("life_drain", new LifeDrainEnchantment());
    public static final Enchantment SWEEPING_ARC = registerEnchantment("sweeping_arc", new SweepingArcEnchantment());

    private static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(MaulvesScythes.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        MaulvesScythes.LOGGER.info("Registering mod enchantments for " + MaulvesScythes.MOD_ID);
    }
}
