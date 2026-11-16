package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.ModItems;
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
					.icon(() -> new ItemStack(ModItems.DIAMOND_SCYTHE.get()))
					.title(Component.translatable("itemgroup.maulves_scythes.scythes"))
					.displayItems((itemDisplayParameters, output) -> {
						output.accept(ModItems.IRON_SCYTHE.get());
						output.accept(ModItems.DIAMOND_SCYTHE.get());
						output.accept(ModItems.NETHERITE_SCYTHE.get());
						output.accept(ModItems.AMETHYST_SCYTHE.get());
						output.accept(ModItems.REINFORCED_STICK.get());
						output.accept(ModItems.SCYTHED_AMETHYST.get());
					}).build()
	);

	public static void initialize() {

	}
}

//? }
