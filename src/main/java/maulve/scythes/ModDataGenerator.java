package maulve.scythes;

import maulve.scythes.datagen.*;
import maulve.scythes.enchantment.ModEnchantments;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModRegistryDataGenerator::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModAdvancementProvider::new);

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            pack.addProvider(ModModelProvider::new);
        }

    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap);
    }
}
