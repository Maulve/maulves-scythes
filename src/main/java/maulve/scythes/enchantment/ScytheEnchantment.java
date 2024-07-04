package maulve.scythes.enchantment;

import maulve.scythes.item.ScytheItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ScytheEnchantment extends Enchantment {
    public ScytheEnchantment() {
        super(Enchantment.Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isAcceptableItem(@NotNull ItemStack stack) {
        return stack.getItem() instanceof ScytheItem;
    }
}
