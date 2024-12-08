package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.custom.AmethystMaterial;
import maulve.scythes.item.custom.AmethystScytheItem;
import maulve.scythes.item.custom.ScytheItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    private static final int ATTACK_DAMAGE = 4;
    private static final float ATTACK_SPEED = -2.8f;

    // -----  Items  -----
    public static final Item REINFORCED_STICK = registerItem("reinforced_stick", new Item.Settings());
    public static final Item SCYTHED_AMETHYST = registerItem("scythed_amethyst",  new Item.Settings());

    // ----- Scythes -----
    public static final Item IRON_SCYTHE = registerScytheItem("iron_scythe",
            ToolMaterial.IRON, ATTACK_DAMAGE + 1, ATTACK_SPEED - 0.1f);

    public static final Item DIAMOND_SCYTHE = registerScytheItem("diamond_scythe",
            ToolMaterial.DIAMOND, ATTACK_DAMAGE, ATTACK_SPEED);

    public static final Item NETHERITE_SCYTHE = registerScytheItem("netherite_scythe",
            ToolMaterial.NETHERITE, ATTACK_DAMAGE, ATTACK_SPEED);

    public static final Item AMETHYST_SCYTHE = Registry.register(Registries.ITEM, Identifier.of(MaulvesScythes.MOD_ID, "amethyst_scythe"),
            new AmethystScytheItem(AmethystMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MaulvesScythes.MOD_ID, "amethyst_scythe")))));

    // -------------------

    private static Item registerItem(String name, Item.Settings settings) {
        Item item = new Item(settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MaulvesScythes.MOD_ID, name))));
        return Registry.register(Registries.ITEM, Identifier.of(MaulvesScythes.MOD_ID, name), item);
    }

    private static Item registerScytheItem(String name, ToolMaterial material, float attackDamage, float attackSpeed) {
        ScytheItem item = new ScytheItem(material, attackDamage, attackSpeed, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MaulvesScythes.MOD_ID, name))));
        return Registry.register(Registries.ITEM, Identifier.of(MaulvesScythes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MaulvesScythes.LOGGER.info("Registering mod items for " + MaulvesScythes.MOD_ID);
    }
}
