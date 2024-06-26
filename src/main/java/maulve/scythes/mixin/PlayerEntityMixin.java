package maulve.scythes.mixin;

import maulve.scythes.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = PlayerEntity.class, priority = 800)
public abstract class PlayerEntityMixin {
    @Unique
    final Item[] scythes = {
            ModItems.IRON_SCYTHE,
            ModItems.DIAMOND_SCYTHE,
            ModItems.NETHERITE_SCYTHE,
            ModItems.AMETHYST_SCYTHE,
    };

    @Unique
    private boolean holdingScythe() {
        for (Item scythe : scythes) {
            PlayerEntity player = MinecraftClient.getInstance().player;
            assert player != null;
            Item heldItem = player.getMainHandStack().getItem();

            if (heldItem == scythe) {
                return true;
            }
        }
        return false;
    }

    // modifies doSweepAttack (bl4) if player is holding a Scythe
    @ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/entity/LivingEntity;)I"), ordinal = 3)
    private boolean overrideBL4(boolean bl4) {
        if (holdingScythe()) {
            return true;
        }
        return bl4;
    }
}