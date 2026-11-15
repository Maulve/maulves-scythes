package maulve.scythes.platform.fabric;

import maulve.scythes.MaulvesScythes;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FabricModCreativeTab {

	public static final ResourceKey<CreativeModeTab> SCYTHES_TAB_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			MaulvesScythes.id("scythes_tab")
	);

	public static final CreativeModeTab SCYTHES_TAB = Registry.register(
			BuiltInRegistries.CREATIVE_MODE_TAB,
			SCYTHES_TAB_KEY,
			FabricItemGroup.builder()
					.icon(() -> new ItemStack(FabricModItems.DIAMOND_SCYTHE))
					.title(Component.translatable("itemgroup.maulves_scythes.scythes"))
					.displayItems((itemDisplayParameters, output) -> {
						output.accept(FabricModItems.IRON_SCYTHE);
						output.accept(FabricModItems.DIAMOND_SCYTHE);
						output.accept(FabricModItems.NETHERITE_SCYTHE);
						output.accept(FabricModItems.AMETHYST_SCYTHE);
						output.accept(FabricModItems.REINFORCED_STICK);
						output.accept(FabricModItems.SCYTHED_AMETHYST);
					}).build()
	);

	public static void initialize() {

	}
}
