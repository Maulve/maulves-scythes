package maulve.scythes.platform.neoforge;

//? neoforge {

/*import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeModCreativeTab {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MaulvesScythes.MOD_ID);

	public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("scythes_tab", () -> CreativeModeTab.builder()
			//Set the title of the tab. Don't forget to add a translation!
			.title(Component.translatable("itemgroup.maulves_scythes.scythes"))
			//Set the icon of the tab.
			.icon(() -> new ItemStack(ModItems.DIAMOND_SCYTHE.get()))
			//Add your items to the tab.
			.displayItems((params, output) -> {
				output.accept(ModItems.IRON_SCYTHE.get());
				output.accept(ModItems.DIAMOND_SCYTHE.get());
				output.accept(ModItems.NETHERITE_SCYTHE.get());
				output.accept(ModItems.AMETHYST_SCYTHE.get());
				output.accept(ModItems.REINFORCED_STICK.get());
				output.accept(ModItems.SCYTHED_AMETHYST.get());
			}).build()
	);

	public static void initialize(IEventBus modEventBus) {
		CREATIVE_MODE_TABS.register(modEventBus);
	}
}

*///? }
