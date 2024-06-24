package maulve.scythes.mixin;

import maulve.scythes.MaulvesScythes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

/*
goal: have sweep attack always happen when scythe is in hand
in method "attack" in PlayerEntity, "bl4" boolean needs to be true
 */

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    //TODO: change Inject time
    @Inject(method = "attack", at = @At("HEAD"))
    public void attack(Entity target, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        String heldItem = player.getMainHandStack().getItem().getName().getString();

        MaulvesScythes.LOGGER.info("Checking attack, held item: " + heldItem);

        //TODO: make item check more better

        if (Objects.equals(heldItem, "Amethyst Scythe")) {
            MaulvesScythes.LOGGER.info("Hit!");
            boolean bl4 = true;
            //TODO: make bl4 be true
        }

    }
}