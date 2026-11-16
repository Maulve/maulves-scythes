package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ItemBlueprint<I extends Item> implements Supplier<I> {
	private final String name;
	private final Function<Item.Properties, I> factory;
	private final UnaryOperator<Item.Properties> propertyModifier;

	private Supplier<I> resolvedSupplier;

	public ItemBlueprint(String name, Function<Item.Properties, I> factory, UnaryOperator<Item.Properties> propertyModifier) {
		this.name = name;
		this.factory = factory;
		this.propertyModifier = propertyModifier;
	}

	public String getName() { return name; }
	public Function<Item.Properties, I> getFactory() { return factory; }
	public UnaryOperator<Item.Properties> getPropertyModifier() { return propertyModifier; }

	// for fabric, neoforge automatically creates the id
	public void setResolvedSupplier(Supplier<I> resolvedSupplier) {
		this.resolvedSupplier = resolvedSupplier;
	}

	@Override
	public I get() {
		if (resolvedSupplier == null) {
			throw new IllegalStateException("Item " + name + " has not been registered yet!");
		}
		return resolvedSupplier.get();
	}
}
