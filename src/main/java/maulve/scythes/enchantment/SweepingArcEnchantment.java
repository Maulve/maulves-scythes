package maulve.scythes.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

public class SweepingArcEnchantment extends ScytheEnchantment {

    @Override
    public boolean canAccept(Enchantment other) {
        return other != Enchantments.SWEEPING && other != Enchantments.FIRE_ASPECT && super.canAccept(other);
    }
}
