package maulve.scythes.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ClientPlayerEntity.class, priority = 800)
public abstract class ClientPlayerEntityMixin extends BasePlayerEntityMixin {}