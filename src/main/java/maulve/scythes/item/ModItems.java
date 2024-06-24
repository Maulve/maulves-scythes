package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe",
            new SwordItem(ToolMaterials.DIAMOND, 2, -1.4f , new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MaulvesScythes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MaulvesScythes.LOGGER.info("Registering mod items for " + MaulvesScythes.MOD_ID);
    }
}
