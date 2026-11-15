package maulve.scythes.mixin;

import maulve.scythes.item.AmethystScytheItem;
import maulve.scythes.item.ScytheItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {

	@Unique
	protected ItemStack getHeldItemStack() {
		Player player = (Player) (Object) this;
		assert player != null;
		return player.getMainHandItem();
	}

	@Unique
	private boolean holdingScythe() {
		return getHeldItemStack().getItem() instanceof ScytheItem;
	}

	@Unique
	private boolean holdingAmethystScythe() {
		return getHeldItemStack().getItem() instanceof AmethystScytheItem;
	}

	// TODO: test
	@ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getHealth()F"), ordinal = 3)
	private boolean overrideDoSweepAttack(boolean doSweepAttack) {
		if (holdingScythe()) {
			return true;
		}
		return doSweepAttack;
	}

	// TODO: bleeding


	// TODO: sweeping arc


}
