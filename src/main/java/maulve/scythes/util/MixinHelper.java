package maulve.scythes.util;

import maulve.scythes.item.ScytheItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class MixinHelper {
	public static ItemStack maulves_scythes$getHeldItemStack(Player player) {
		assert player != null;
		return player.getMainHandItem();
	}

	public static boolean maulves_scythes$holdingScythe(Player player) {
		return maulves_scythes$getHeldItemStack(player).getItem() instanceof ScytheItem;
	}
}
