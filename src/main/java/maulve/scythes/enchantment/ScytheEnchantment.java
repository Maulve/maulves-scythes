package maulve.scythes.enchantment;

import maulve.scythes.item.ScytheItem;
import maulve.scythes.MaulvesScythes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

public class ScytheEnchantment extends Enchantment {
    public ScytheEnchantment() {
        super(new Enchantment.Properties(
                TagKey.of(Registries.ITEM.getKey(), new Identifier(MaulvesScythes.MOD_ID, "scythe_enchantable")), // Supported items tag
                java.util.Optional.ofNullable(TagKey.of(Registries.ITEM.getKey(), new Identifier(MaulvesScythes.MOD_ID, "scythe"))), // Primary items tag
                5, // Weight
                3, // Max level
                new Enchantment.Cost(5, 8), // Min cost
                new Enchantment.Cost(50, 58), // Max cost
                2, // Anvil cost
                FeatureSet.empty(),
                new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ScytheItem;
    }
}
