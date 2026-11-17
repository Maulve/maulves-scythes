package maulve.scythes.enchantment;

import maulve.scythes.util.ScythesEnchantmentHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ScytheEnchantment extends Enchantment {
	private final ScythesEnchantmentHelper.Definition def;

	public ScytheEnchantment(ScythesEnchantmentHelper.Definition def) {
		super(mapWeightToRarity(def.weight()), EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
		this.def = def;
	}

	private static Rarity mapWeightToRarity(int weight) {
		if (weight >= 10) return Rarity.COMMON;
		if (weight >= 5) return Rarity.UNCOMMON;
		if (weight >= 2) return Rarity.RARE;
		return Rarity.VERY_RARE;
	}

	@Override
	public int getMinCost(int level) {
		return def.minCost().calculate(level);
	}

	@Override
	public int getMaxCost(int level) {
		return def.maxCost().calculate(level);
	}

	@Override
	public int getMaxLevel() {
		return def.maxLevel();
	}

	@Override
	public boolean canEnchant(ItemStack stack) {
		return stack.is(def.supportedItems());
	}

	@Override
	protected boolean checkCompatibility(Enchantment other) {
		Identifier otherId = BuiltInRegistries.ENCHANTMENT.getKey(other);
		if (otherId != null && def.exclusiveIds().contains(otherId)) {
			return false;
		}
		return super.checkCompatibility(other);
	}
}
