package maulve.scythes.item;

//? < 1.21.11 {
/*import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
*///? }

public class AmethystScytheItem extends ScytheItem {
	//? if < 1.21.11 {
	/*public AmethystScytheItem(ToolMaterial toolMaterial, Item.Settings settings) {
		super(toolMaterial, settings);
	}
	*///? } else {
	public AmethystScytheItem(Properties properties) {
		super(properties);
	}
	//? }
}
