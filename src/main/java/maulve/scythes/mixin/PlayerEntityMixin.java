package maulve.scythes.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.custom.AmethystScytheItem;
import maulve.scythes.item.custom.ScytheItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.Set;

@Mixin(value = PlayerEntity.class, priority = 800)
public abstract class PlayerEntityMixin {

    // Helper Functions

    @Unique
    protected ItemStack getHeldItemStack() {
        PlayerEntity player = (PlayerEntity)(Object) this;
        return player.getMainHandStack();
    }

    @Unique
    private boolean holdingScythe() {
        return getHeldItemStack().getItem() instanceof ScytheItem;
    }

    @Unique
    private boolean holdingAmethystScythe() {
        return getHeldItemStack().getItem() instanceof AmethystScytheItem;
    }

    @Unique
    private boolean hasWideSweepEnchantment() {
        ItemStack itemStack = getHeldItemStack();

        if (itemStack.getItem() instanceof ScytheItem) {
            Set<RegistryEntry<Enchantment>> enchantments = itemStack.getEnchantments().getEnchantments();
            for (RegistryEntry<Enchantment> enchantment : enchantments) {
                if (Objects.equals(enchantment.getIdAsString(), MaulvesScythes.MOD_ID + ":sweeping_arc")) {
                    return true;
                }
            }

        }
        return false;
    }

    // ----------------------------

    // modifies doSweepAttack (bl4) if player is holding a Scythe
    @ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHealth()F"), ordinal = 3)
    private boolean overrideBL4(boolean bl4) {
        if (holdingScythe()) {
            return true;
        }
        return bl4;
    }

    // adds Bleeding to sweeping attack targets
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;serverDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void addBleeding(Entity target, CallbackInfo ci, @Local(ordinal = 0) @NotNull LivingEntity livingEntity) {
        if (holdingAmethystScythe()) {
            StatusEffectInstance effectWither = new StatusEffectInstance(StatusEffects.WITHER, 80, 1);
            livingEntity.addStatusEffect(effectWither);
        }
    }

    // ----------------------------
    // For Sweeping Arc enchantment

    @Unique
    private Entity attackTarget;

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getNonSpectatingEntities(Ljava/lang/Class;Lnet/minecraft/util/math/Box;)Ljava/util/List;"))
    private void captureTarget(Entity target, CallbackInfo ci) {
        this.attackTarget = target;
    }

    @ModifyArg(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getNonSpectatingEntities(Ljava/lang/Class;Lnet/minecraft/util/math/Box;)Ljava/util/List;"), index = 1)
    private Box modifyBox(Box box) {
        if (hasWideSweepEnchantment()) {
            return attackTarget.getBoundingBox().expand(10.0, 0.5, 10.0);
        }
        return box;
    }
}