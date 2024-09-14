package maulve.scythes.mixin;

import maulve.scythes.item.ScytheItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Unique;

//@Mixin(value = PlayerEntity.class, priority = 800)
public abstract class ClientPlayerEntityMixin {
    @Unique
    private boolean holdingScythe() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        Item heldItem = player.getMainHandStack().getItem();

        return heldItem instanceof ScytheItem;
    }

    // modifies doSweepAttack (bl4) if player is holding a Scythe
    //@ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/entity/LivingEntity;)I"), ordinal = 3)
    private boolean overrideBL4(boolean bl4) {
        if (holdingScythe()) {
            return true;
        }
        return bl4;
    }
}