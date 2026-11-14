package maulve.scythes.mixin;

import maulve.scythes.MaulvesScythes;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class PlayerEntityMixin {

	@Inject(method = "loadLevel", at = @At("RETURN"))
	private void afterLoadLevel(CallbackInfo ci) {
		MaulvesScythes.LOGGER.info("Level Loaded!");
	}

}
