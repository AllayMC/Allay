package org.allaymc.server;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.component.attribute.VanillaBlockAttributeRegistry;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.blockentity.registry.BlockEntityTypeRegistry;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.command.CommandManager;
import org.allaymc.api.component.interfaces.ComponentInjector;
import org.allaymc.api.data.VanillaItemMetaBlockStateBiMap;
import org.allaymc.api.datastruct.DynamicURLClassLoader;
import org.allaymc.api.entity.component.EntityComponentImplFactory;
import org.allaymc.api.entity.effect.EffectRegistry;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.exception.MissingImplementationException;
import org.allaymc.api.item.component.attribute.VanillaItemAttributeRegistry;
import org.allaymc.api.item.enchantment.EnchantmentRegistry;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.allaymc.api.item.registry.CreativeItemRegistry;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.allaymc.server.block.attribute.AllayVanillaBlockAttributeRegistry;
import org.allaymc.server.block.registry.AllayBlockStateHashPalette;
import org.allaymc.server.block.registry.AllayBlockTypeRegistry;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.blockentity.registry.AllayBlockEntityTypeRegistry;
import org.allaymc.server.blockentity.type.AllayBlockEntityType;
import org.allaymc.server.command.AllayCommandManager;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.data.AllayVanillaItemMetaBlockStateBiMap;
import org.allaymc.server.entity.component.AllayEntityComponentImplFactory;
import org.allaymc.server.entity.effect.AllayEffectRegistry;
import org.allaymc.server.entity.registry.AllayEntityTypeRegistry;
import org.allaymc.server.entity.type.AllayEntityType;
import org.allaymc.server.item.attribute.AllayVanillaItemAttributeRegistry;
import org.allaymc.server.item.enchantment.AllayEnchantmentRegistry;
import org.allaymc.server.item.recipe.AllayRecipeRegistry;
import org.allaymc.server.item.registry.AllayCreativeItemRegistry;
import org.allaymc.server.item.registry.AllayItemTypeRegistry;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.allaymc.server.world.biome.AllayBiomeTypeRegistry;
import org.jetbrains.annotations.VisibleForTesting;

@Slf4j
public final class Allay {

    public static final DynamicURLClassLoader EXTRA_RESOURCE_CLASS_LOADER = new DynamicURLClassLoader(Allay.class.getClassLoader());

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.setProperty("joml.format", "false"); // set JOML vectors are output without scientific notation
        log.info("Starting Allay...");
        try {
            initAllayAPI();
            ComponentClassCacheUtils.saveCacheMapping();
        } catch (Exception e) {
            log.error("Cannot init Allay API!", e);
            System.exit(1);
        }
        Server.getInstance().start(startTime);
    }

    @VisibleForTesting
    public static void initAllayAPI() throws MissingImplementationException {
        var api = AllayAPI.getInstance();
        if (api.isImplemented()) return;

        ComponentClassCacheUtils.checkCacheValid();
        ComponentClassCacheUtils.readCacheMapping();

        // Common
        api.bind(ComponentInjector.ComponentInjectorFactory.class, () -> AllayComponentInjector::new);
        api.bind(Server.class, AllayServer::getInstance);
        api.bind(Scheduler.SchedulerFactory.class, () -> asyncTaskExecutor -> {
            if (asyncTaskExecutor == null) return new AllayScheduler();
            else return new AllayScheduler(asyncTaskExecutor);
        });

        // Item
        api.bind(EnchantmentRegistry.class, AllayEnchantmentRegistry::new, instance -> ((AllayEnchantmentRegistry) instance).init());
        api.bind(ItemTypeBuilder.ItemTypeBuilderFactory.class, () -> AllayItemType::builder);
        api.bind(VanillaItemAttributeRegistry.class, () -> new AllayVanillaItemAttributeRegistry(new AllayVanillaItemAttributeRegistry.Loader()));
        api.bind(ItemTypeRegistry.class, AllayItemTypeRegistry::new, instance -> ((AllayItemTypeRegistry) instance).init());

        // BlockEntity
        api.bind(BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory.class, () -> AllayBlockEntityType::builder);
        api.bind(BlockEntityTypeRegistry.class, AllayBlockEntityTypeRegistry::new, instance -> ((AllayBlockEntityTypeRegistry) instance).init());

        // Block
        api.bind(BlockTypeBuilder.BlockTypeBuilderFactory.class, () -> AllayBlockType::builder);
        api.bind(VanillaBlockAttributeRegistry.class, () -> new AllayVanillaBlockAttributeRegistry(new AllayVanillaBlockAttributeRegistry.Loader()));
        api.bind(BlockStateHashPalette.class, AllayBlockStateHashPalette::new);
        api.bind(BlockTypeRegistry.class, AllayBlockTypeRegistry::new, instance -> ((AllayBlockTypeRegistry) instance).init());

        // Entity
        api.bind(EntityComponentImplFactory.class, AllayEntityComponentImplFactory::new);
        api.bind(EffectRegistry.class, AllayEffectRegistry::new, instance -> ((AllayEffectRegistry) instance).init());
        api.bind(EntityTypeBuilder.EntityTypeBuilderFactory.class, () -> AllayEntityType::builder);
        api.bind(EntityTypeRegistry.class, AllayEntityTypeRegistry::new, instance -> ((AllayEntityTypeRegistry) instance).init());

        // Biome
        api.bind(BiomeTypeRegistry.class, AllayBiomeTypeRegistry::new);

        // Misc
        api.bind(VanillaItemMetaBlockStateBiMap.class, AllayVanillaItemMetaBlockStateBiMap::new, instance -> ((AllayVanillaItemMetaBlockStateBiMap) instance).init());

        // Creative Item Registry
        api.bind(CreativeItemRegistry.class, () -> new AllayCreativeItemRegistry(new AllayCreativeItemRegistry.Loader()));

        // Recipe
        api.bind(RecipeRegistry.class, AllayRecipeRegistry::new, instance -> ((AllayRecipeRegistry) instance).registerVanillaRecipes());

        // Command
        api.bind(CommandManager.class, AllayCommandManager::new, instance -> ((AllayCommandManager) instance).init());

        api.implement("Allay");
    }
}