package maulve.scythes.enchantment;

import maulve.scythes.MaulvesScythes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//INFO: currently unused until there are ideas for enchantments

public class ModEnchantments {
    // public static final Enchantment SCYTHE_ENCHANT = registerEnchantment("scythe_enchantment", new ScytheEnchantment());

    private static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(MaulvesScythes.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        MaulvesScythes.LOGGER.info("Registering mod enchantments for " + MaulvesScythes.MOD_ID);
    }
}
