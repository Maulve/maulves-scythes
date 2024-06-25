package maulve.scythes.mixin;

import maulve.scythes.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    @Unique
    final Item[] scythes = {
            ModItems.IRON_SCYTHE,
            ModItems.DIAMOND_SCYTHE,
            ModItems.NETHERITE_SCYTHE,
            ModItems.AMETHYST_SCYTHE,
    };

    @Unique
    private boolean holdingScythe() {
        for (int i = 0; i < scythes.length; i++) {
            Item scythe = scythes[i];

            PlayerEntity player = MinecraftClient.getInstance().player;
            assert player != null;
            Item heldItem = player.getMainHandStack().getItem();

            if (heldItem == scythe) {
                return true;
            }
        }
        return false;
    }

    @ModifyVariable(method = "attack", at = @At("STORE"), name = "bl4")
    public boolean attack(boolean bl4) {
        if (holdingScythe()) {
            return true;
        }
        return bl4;
    }
}