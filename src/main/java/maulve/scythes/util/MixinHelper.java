package maulve.scythes.util;

import maulve.scythes.item.AmethystScytheItem;
import maulve.scythes.item.ScytheItem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class MixinHelper {
	public static ItemStack maulves_scythes$getHeldItemStack(Player player) {
		assert player != null;
		return player.getMainHandItem();
	}

	public static boolean maulves_scythes$holdingScythe(Player player) {
		return maulves_scythes$getHeldItemStack(player).getItem() instanceof ScytheItem;
	}

	public static boolean maulves_scythes$holdingAmethystScythe(Player player) {
		return maulves_scythes$getHeldItemStack(player).getItem() instanceof AmethystScytheItem;
	}

	public static boolean maulves_scythes$hasEnchantment(Player player, ResourceKey<Enchantment> key) {
		Holder<Enchantment> sweepingArcHolder = player.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(key);

		return EnchantmentHelper.getEnchantmentLevel(sweepingArcHolder, player) > 0;
	}

}
