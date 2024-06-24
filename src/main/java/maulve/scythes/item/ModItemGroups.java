package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SCYTHE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MaulvesScythes.MOD_ID, "scythe"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.scythe"))
                    .icon(() -> new ItemStack(ModItems.DIAMOND_SCYTHE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.DIAMOND_SCYTHE);
                    }).build());

    public static void registerItemGroups() {
        MaulvesScythes.LOGGER.info("Registering Item Groups for " + MaulvesScythes.MOD_ID);
    }
}
