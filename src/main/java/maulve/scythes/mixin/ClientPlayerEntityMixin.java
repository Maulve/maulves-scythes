package maulve.scythes.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ClientPlayerEntity.class, priority = 800)
public abstract class ClientPlayerEntityMixin extends BasePlayerEntityMixin {
    @Override
    protected ItemStack getHeldItemStack() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        return player.getMainHandStack();
    }
}