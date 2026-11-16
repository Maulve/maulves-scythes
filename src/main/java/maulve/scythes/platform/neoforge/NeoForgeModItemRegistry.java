package maulve.scythes.platform.neoforge;

//? neoforge {

/*import maulve.scythes.MaulvesScythes;
import maulve.scythes.item.ItemBlueprint;
import maulve.scythes.item.ModItems;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeModItemRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MaulvesScythes.MOD_ID);

	public static void initialize(IEventBus modEventBus) {
		for (ItemBlueprint<?> blueprint : ModItems.getBlueprints()) {
			DeferredItem<?> deferredItem = ITEMS.registerItem(
					blueprint.getName(),
					blueprint.getFactory(),
					//? if >= 1.21.11 {
					/^blueprint.getPropertyModifier()
					^///? } else {
					blueprint.getPropertyModifier().apply(new Item.Properties())
					//? }
			);

			wireSupplier(blueprint, deferredItem);
		}

		ITEMS.register(modEventBus);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Item> void wireSupplier(ItemBlueprint<?> blueprint, DeferredItem<?> deferredItem) {
		((ItemBlueprint<T>) blueprint).setResolvedSupplier((Supplier<T>) deferredItem);
	}
}

*///? }
