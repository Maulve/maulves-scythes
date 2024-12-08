package maulve.scythes.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ServerPlayerEntity.class, priority = 800)
public abstract class ServerPlayerEntityMixin extends BasePlayerEntityMixin {
    @Override
    protected ItemStack getHeldItemStack() {
        PlayerEntity player = (PlayerEntity)(Object) this;
        return player.getMainHandStack();
    }
}