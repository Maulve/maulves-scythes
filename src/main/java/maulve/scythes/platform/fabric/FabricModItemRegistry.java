package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.ItemBlueprint;
import maulve.scythes.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class FabricModItemRegistry {

	public static void initialize() {
		for (ItemBlueprint<?> blueprint : ModItems.getBlueprints()) {
			ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, MaulvesScythes.id(blueprint.getName()));

			Item.Properties properties = blueprint.getPropertyModifier().apply(new Item.Properties());

			//? if >= 1.21.4 {
			properties.setId(key);
			//? }

			Item item = blueprint.getFactory().apply(properties);

			Item registeredItem = Registry.register(BuiltInRegistries.ITEM, key, item);

			wireSupplier(blueprint, registeredItem);
		}
	}

	@SuppressWarnings("unchecked")
	private static <I extends Item> void wireSupplier(ItemBlueprint<?> blueprint, Item item) {
		((ItemBlueprint<I>) blueprint).setResolvedSupplier(() -> (I) item);
	}

}

//? }
