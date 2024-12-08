package maulve.scythes.enchantment;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.util.ModItemTags;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> LIFE_DRAIN = of("life_drain");
    public static final RegistryKey<Enchantment> SWEEPING_ARC = of("sweeping_arc");

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, LIFE_DRAIN, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModItemTags.SCYTHES),
                items.getOrThrow(ModItemTags.SCYTHES),
                2,
                3,
                Enchantment.leveledCost(10, 10),
                Enchantment.leveledCost(50, 20),
                4,
                AttributeModifierSlot.MAINHAND)));
        // TODO: add exclusiveSet (non-compatible enchants)

        register(registerable, SWEEPING_ARC, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModItemTags.SCYTHES),
                items.getOrThrow(ModItemTags.SCYTHES),
                2,
                1,
                Enchantment.leveledCost(10, 10),
                Enchantment.leveledCost(50, 20),
                4,
                AttributeModifierSlot.MAINHAND)));
        // TODO: add exclusiveSet (non-compatible enchants)
    }


    private static RegistryKey<Enchantment> of(final String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MaulvesScythes.MOD_ID, name));
    }

    public static RegistryEntry<Enchantment> getEntry(DynamicRegistryManager registryManager, RegistryKey<Enchantment> key) {
        Registry<Enchantment> registry = registryManager.getOrThrow(RegistryKeys.ENCHANTMENT);
        return registry.getOrThrow(key);
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
