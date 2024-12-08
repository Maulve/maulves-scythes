package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.custom.AmethystMaterial;
import maulve.scythes.item.custom.AmethystScytheItem;
import maulve.scythes.item.custom.ScytheItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static final int ATTACK_DAMAGE = 4;
    private static final float ATTACK_SPEED = -2.8f;

    // -----  Items  -----
    public static final Item REINFORCED_STICK = registerItem("reinforced_stick", new Item(new Item.Settings()));
    public static final Item SCYTHED_AMETHYST = registerItem("scythed_amethyst", new Item(new Item.Settings()));

    // ----- Scythes -----
    public static final Item IRON_SCYTHE = registerItem("iron_scythe",
            new ScytheItem(ToolMaterials.IRON,
                    new Item.Settings().attributeModifiers(ScytheItem.createAttributeModifiers(ToolMaterials.IRON, ATTACK_DAMAGE + 1, ATTACK_SPEED - 0.1f))));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe",
            new ScytheItem(ToolMaterials.DIAMOND,
                    new Item.Settings().attributeModifiers(ScytheItem.createAttributeModifiers(ToolMaterials.DIAMOND, ATTACK_DAMAGE, ATTACK_SPEED))));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe",
            new ScytheItem(ToolMaterials.NETHERITE,
                    new Item.Settings().attributeModifiers(ScytheItem.createAttributeModifiers(ToolMaterials.NETHERITE, ATTACK_DAMAGE, ATTACK_SPEED))));

    public static final Item AMETHYST_SCYTHE = registerItem("amethyst_scythe",
            new AmethystScytheItem(AmethystMaterial.INSTANCE,
                    new Item.Settings().attributeModifiers(ScytheItem.createAttributeModifiers(AmethystMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MaulvesScythes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MaulvesScythes.LOGGER.info("Registering mod items for " + MaulvesScythes.MOD_ID);
    }
}
