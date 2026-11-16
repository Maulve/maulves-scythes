package maulve.scythes.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import maulve.scythes.enchantment.ModEnchantments;
import maulve.scythes.item.AmethystScytheItem;
import maulve.scythes.item.ScytheItem;
import maulve.scythes.platform.fabric.FabricModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
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

	@Unique boolean hasSweepingArcEnchantment() {
		Player player = (Player) (Object) this;

		Holder<Enchantment> sweepingArcHolder = player.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ModEnchantments.SWEEPING_ARC);

		return EnchantmentHelper.getEnchantmentLevel(sweepingArcHolder, player) > 0;
	}

	@ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getHealth()F"), ordinal = 3)
	private boolean overrideDoSweepAttack(boolean doSweepAttack) {
		if (holdingScythe()) {
			return true;
		}
		return doSweepAttack;
	}

	@ModifyArg(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtOrSimulate(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 0)
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

	@ModifyExpressionValue(method = "doSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(DDD)Lnet/minecraft/world/phys/AABB;"))
	private AABB expandSweepBoundingBoxForSweepingArc(AABB originalBox) {
		if (hasSweepingArcEnchantment()) {
			return originalBox.inflate(10.0, 0.5, 10.0);
		} else {
			return originalBox;
		}
	}
}
