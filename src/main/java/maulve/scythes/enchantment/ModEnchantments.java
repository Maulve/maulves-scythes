package maulve.scythes.enchantment;

import maulve.scythes.MaulvesScythes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final ScytheEnchantment LIFE_DRAIN = register("life_drain", new LifeDrainEnchantment());

    private static ScytheEnchantment register(String name, ScytheEnchantment enchantment) {
        MaulvesScythes.LOGGER.info("Registering: " + enchantment.toString());
        return Registry.register(Registries.ENCHANTMENT, new Identifier(MaulvesScythes.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        MaulvesScythes.LOGGER.info("Registering mod enchantments for " + MaulvesScythes.MOD_ID);
    }
}
