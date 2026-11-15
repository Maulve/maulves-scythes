package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.MaulvesScythes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.network.chat.Component;

public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MaulvesScythes.onInitializeClient();

		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, list) -> {
			if (stack.is(FabricModItems.AMETHYST_SCYTHE)) {
				list.add(Component.translatable("item.maulves_scythes.amethyst_scythe.tooltip-bleeding"));
			}
		});
	}
}
//?}
