package maulve.scythes.util;

//? if >= 1.21.4 {
import maulve.scythes.item.ModToolMaterials;
import net.minecraft.world.item.ToolMaterial;
//? } else {
/*import maulve.scythes.item.ModTiers;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
*///? }

// for helping get ToolMaterial vs. Tier
public class MaterialHelper {
	public enum Key {
		IRON,
		DIAMOND,
		NETHERITE,
		AMETHYST,
	}

	//? if >= 1.21.4 {
	public static ToolMaterial get(Key key) {
		//? } else {
	/*public static Tier get(Key key) {
		*///? }
		//? if >= 1.21.4 {
		return switch (key) {
			case IRON -> ToolMaterial.IRON;
			case DIAMOND -> ToolMaterial.DIAMOND;
			case NETHERITE -> ToolMaterial.NETHERITE;
			case AMETHYST -> ModToolMaterials.AMETHYST;
		};
		//? } else {
		/*return switch (key) {
			case IRON -> Tiers.IRON;
			case DIAMOND -> Tiers.DIAMOND;
			case NETHERITE -> Tiers.NETHERITE;
			case AMETHYST -> ModTiers.AMETHYST;
		};
		*///? }
	}
}
