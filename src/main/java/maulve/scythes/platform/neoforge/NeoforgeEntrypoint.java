package maulve.scythes.platform.neoforge;

//? neoforge {

/*import maulve.scythes.MaulvesScythes;
import maulve.scythes.enchantment.ModEnchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(MaulvesScythes.MOD_ID)
public class NeoforgeEntrypoint {
	public NeoforgeEntrypoint(IEventBus modEventBus) {
		MaulvesScythes.onInitialize();
		//? <= 1.20.4
		ModEnchantments.initialize(modEventBus);
		NeoForgeModItemRegistry.initialize(modEventBus);
		NeoForgeMobEffects.initialize(modEventBus);
		NeoForgeModCreativeTab.initialize(modEventBus);


	}
}
*///?}
