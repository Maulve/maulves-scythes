package maulve.scythes.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import maulve.scythes.item.AmethystScytheItem;
import maulve.scythes.item.ScytheItem;
import maulve.scythes.platform.fabric.FabricModEffects;
import maulve.scythes.platform.fabric.FabricModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

	@ModifyArg(
			method = "attack",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/Entity;hurtOrSimulate(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
			), index = 0
	)
	private DamageSource modifyDamageSource(DamageSource originalSource) {
		Player player = (Player) (Object) this;
		if (holdingAmethystScythe()) {
			return new DamageSource(
					player.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
							.getOrThrow(FabricModItems.CRYSTAL_SWING),
					player
			);
		}

		return originalSource;
	}

	// TODO: sweeping arc
}
