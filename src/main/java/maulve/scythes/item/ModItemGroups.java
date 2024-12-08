package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
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
                        entries.add(ModItems.IRON_SCYTHE);
                        entries.add(ModItems.DIAMOND_SCYTHE);
                        entries.add(ModItems.NETHERITE_SCYTHE);
                        entries.add(ModItems.AMETHYST_SCYTHE);
                        entries.add(ModItems.REINFORCED_STICK);
                        entries.add(ModItems.SCYTHED_AMETHYST);
                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.LIFE_DRAIN, 1)));
                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.LIFE_DRAIN, 2)));
                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.LIFE_DRAIN, 3)));
                        entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.SWEEPING_ARC, 1)));
                    }).build());

    public static void registerItemGroups() {
        MaulvesScythes.LOGGER.info("Registering Item Groups for " + MaulvesScythes.MOD_ID);
    }
}
