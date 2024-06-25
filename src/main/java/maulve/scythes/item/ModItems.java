package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static final int ATTACK_DAMAGE = 4;
    private static final float ATTACK_SPEED = -2.8f;

    public static final Item REINFORCED_STICK = registerItem("reinforced_stick", new Item(new FabricItemSettings()));

    public static final Item IRON_SCYTHE = registerItem("iron_scythe",
            new SwordItem(ToolMaterials.IRON, ATTACK_DAMAGE + 1, ATTACK_SPEED - 0.1f, new FabricItemSettings()));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe",
            new SwordItem(ToolMaterials.DIAMOND, ATTACK_DAMAGE, ATTACK_SPEED , new FabricItemSettings()));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe",
            new SwordItem(ToolMaterials.NETHERITE, ATTACK_DAMAGE, ATTACK_SPEED, new FabricItemSettings()));

    public static final Item AMETHYST_SCYTHE = registerItem("amethyst_scythe",
            new SwordItem(ModToolMaterial.AMETHYST, ATTACK_DAMAGE, ATTACK_SPEED, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MaulvesScythes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MaulvesScythes.LOGGER.info("Registering mod items for " + MaulvesScythes.MOD_ID);
    }
}
