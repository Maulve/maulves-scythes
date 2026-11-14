package maulve.scythes.event;

import maulve.scythes.MaulvesScythes;
import net.minecraft.server.level.ServerPlayer;

public class ExampleEventHandler {
	public static void onPlayerHurt(ServerPlayer player) {
		MaulvesScythes.LOGGER.info("onPlayerHurt");
	}
}
