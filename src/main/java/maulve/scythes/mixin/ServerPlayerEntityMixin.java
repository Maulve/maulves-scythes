package maulve.scythes.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = ServerPlayerEntity.class, priority = 800)
public abstract class ServerPlayerEntityMixin extends BasePlayerEntityMixin {
    @Unique
    @Override
    protected ItemStack getHeldItemStack() {
        PlayerEntity player = (PlayerEntity) (Object) this;
        assert player != null;
        return player.getMainHandStack();
    }
}