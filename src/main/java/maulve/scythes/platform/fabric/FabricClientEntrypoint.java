package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.MaulvesScythes;
import maulve.scythes.effect.CrystallizedMobEffect;
import maulve.scythes.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MaulvesScythes.onInitializeClient();

		//? if >= 1.21.1 {
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, list) -> {
			//? } else {
		/*ItemTooltipCallback.EVENT.register((stack, tooltipContext, list) -> {
			*///? }
			if (stack.is(ModItems.AMETHYST_SCYTHE.get())) {
				list.add(CrystallizedMobEffect.TOOLTIP);
			}
		});
	}
}
//?}
