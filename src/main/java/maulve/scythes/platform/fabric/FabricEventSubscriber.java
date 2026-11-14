package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.event.ExampleEventHandler; // sample_content
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents; // sample_content
import net.minecraft.server.level.ServerPlayer; // sample_content

public class FabricEventSubscriber {

	public static void registerEvents() {
		// this event just doesn't exist in 1.20.4
		//? > 1.20.4 {
		// sample_content
		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamage, damageTaken, blocked) -> { // sample_content
			if (entity instanceof ServerPlayer && damageTaken > 0) { // sample_content
				ExampleEventHandler.onPlayerHurt((ServerPlayer) entity); // sample_content
			} // sample_content
		}); // sample_content
		//?}
	}
}
//?}
