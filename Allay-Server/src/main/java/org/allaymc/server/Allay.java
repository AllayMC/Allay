package org.allaymc.server;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.component.interfaces.ComponentInjector;
import org.allaymc.api.data.VanillaItemMetaBlockStateBiMap;
import org.allaymc.api.datastruct.DynamicURLClassLoader;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.pack.PackRegistry;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.registry.*;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.exception.MissingImplementationException;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.blockentity.type.AllayBlockEntityType;
import org.allaymc.server.command.selector.AllayEntitySelectorAPI;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.data.AllayVanillaItemMetaBlockStateBiMap;
import org.allaymc.server.entity.type.AllayEntityType;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.gui.Dashboard;
import org.allaymc.server.i18n.AllayI18n;
import org.allaymc.server.i18n.AllayI18nLoader;
import org.allaymc.server.item.registry.AllayRecipeRegistry;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.pack.AllayPackRegistry;
import org.allaymc.server.perm.tree.AllayPermTree;
import org.allaymc.server.registry.loader.*;
import org.allaymc.server.registry.populator.*;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.allaymc.server.world.biome.AllayBiomeTypeRegistry;
import org.allaymc.server.world.generator.AllayWorldGenerator;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public final class Allay {

    public static final DynamicURLClassLoader EXTRA_RESOURCE_CLASS_LOADER = new DynamicURLClassLoader(Allay.class.getClassLoader());

    public static Dashboard DASHBOARD;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.setProperty("joml.format", "false"); // Set JOML vectors are output without a scientific notation
        System.setProperty("log4j2.contextSelector", AsyncLoggerContextSelector.class.getName()); // Enable async logging
        initI18n();
        // Check if the environment is headless
        if (isHeadless()) Server.SETTINGS.genericSettings().enableGui(false);

        if (Server.SETTINGS.genericSettings().enableGui()) {
            try {
                DASHBOARD = Dashboard.getInstance();
            } catch (Throwable t) {
                log.error("Cannot init Dashboard!", t);
                Server.SETTINGS.genericSettings().enableGui(false);
            }
        }

        log.info(I18n.get().tr(TrKeys.A_SERVER_STARTING));
        try {
            initAllay();
            // Only save cache mapping when allay is running normally
            // instead of running in test environment
            ComponentClassCacheUtils.saveCacheMapping();
        } catch (Exception e) {
            log.error("Cannot init Allay API!", e);
            System.exit(1);
        }

        try {
            Server.getInstance().start(startTime);
        } catch (Exception e) {
            log.error("Error while starting the server!", e);
            Server.getInstance().shutdown();
        }
    }

    private static boolean isHeadless() {
        try {
            var graphicsEnv = Class.forName("java.awt.GraphicsEnvironment");
            var isHeadless = graphicsEnv.getDeclaredMethod("isHeadless");
            return (boolean) isHeadless.invoke(null);
        } catch (Exception ignore) {}
        return true;
    }


    /**
     * NOTICE: The i18n implementation must be registered before initializing allay,
     * which means that you should call initI18n() before call initAllay()!
     */
    @VisibleForTesting
    public static void initAllay() throws MissingImplementationException {
        initAllayAPI();
        initRegistries();
    }
    private static void initAllayAPI() throws MissingImplementationException {
        var api = AllayAPI.getInstance();
        if (api.isImplemented()) return;

        // Check if the cache has expired
        ComponentClassCacheUtils.checkCacheValid();
        // Read component class cache
        ComponentClassCacheUtils.readCacheMapping();

        // Common
        api.bind(ComponentInjector.ComponentInjectorFactory.class, () -> AllayComponentInjector::new);
        api.bind(Server.class, AllayServer::getInstance);
        api.bind(Scheduler.SchedulerFactory.class, () -> asyncTaskExecutor -> {
            if (asyncTaskExecutor == null) return new AllayScheduler();
            else return new AllayScheduler(asyncTaskExecutor);
        });
        api.bind(EventBus.EventBusFactory.class, () -> AllayEventBus::new);

        // Item
//        api.bind(EnchantmentRegistry.class, AllayEnchantmentRegistry::new, instance -> ((AllayEnchantmentRegistry) instance).init());
        api.bind(ItemTypeBuilder.ItemTypeBuilderFactory.class, () -> AllayItemType::builder);
//        api.bind(VanillaItemDataRegistry.class, () -> new AllayVanillaItemDataRegistry(new AllayVanillaItemDataRegistry.Loader()));
//        api.bind(ItemTypeRegistry.class, AllayItemTypeRegistry::new, instance -> ((AllayItemTypeRegistry) instance).init());

        // BlockEntity
        api.bind(BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory.class, () -> AllayBlockEntityType::builder);
//        api.bind(BlockEntityTypeRegistry.class, AllayBlockEntityTypeRegistry::new, instance -> ((AllayBlockEntityTypeRegistry) instance).init());

        // Block
//        api.bind(MaterialRegistry.class, () -> new AllayMaterialRegistry(new AllayMaterialRegistry.Loader()));
        api.bind(BlockTypeBuilder.BlockTypeBuilderFactory.class, () -> AllayBlockType::builder);
//        api.bind(VanillaBlockStateDataRegistry.class, () -> new AllayVanillaBlockStateDataRegistry(new AllayVanillaBlockStateDataRegistry.Loader()));
//        api.bind(BlockStateHashPalette.class, AllayBlockStateHashPalette::new);
//        api.bind(BlockTypeRegistry.class, AllayBlockTypeRegistry::new, instance -> ((AllayBlockTypeRegistry) instance).init());

        // Entity
//        api.bind(EffectRegistry.class, AllayEffectRegistry::new, instance -> ((AllayEffectRegistry) instance).init());
        api.bind(EntityTypeBuilder.EntityTypeBuilderFactory.class, () -> AllayEntityType::builder);
//        api.bind(EntityTypeRegistry.class, AllayEntityTypeRegistry::new, instance -> ((AllayEntityTypeRegistry) instance).init());

        // Biome
        api.bind(BiomeTypeRegistry.class, AllayBiomeTypeRegistry::new);

        // Misc
        api.bind(VanillaItemMetaBlockStateBiMap.class, AllayVanillaItemMetaBlockStateBiMap::new, instance -> ((AllayVanillaItemMetaBlockStateBiMap) instance).init());

        // World
        api.bind(WorldGenerator.WorldGeneratorBuilderFactory.class, () -> AllayWorldGenerator::builder);
//        api.bind(WorldStorageFactory.class, AllayWorldStorageFactory::new);
//        api.bind(WorldGeneratorFactory.class, AllayWorldGeneratorFactory::new);

        // Creative Item Registry
//        api.bind(CreativeItemRegistry.class, () -> new AllayCreativeItemRegistry(new AllayCreativeItemRegistry.Loader()));

        // Recipe
        api.bind(RecipeRegistry.class, AllayRecipeRegistry::new, instance -> ((AllayRecipeRegistry) instance).registerVanillaRecipes());

        // Perm
        api.bind(PermTree.PermTreeFactory.class, () -> AllayPermTree::create);

        // Command
        api.bind(EntitySelectorAPI.class, AllayEntitySelectorAPI::new);
        api.bind(CommandTree.CommandTreeFactory.class, () -> AllayCommandTree::create);
        api.bind(CommandNodeFactory.class, AllayCommandNodeFactory::new);

        // Resource pack
        api.bind(PackRegistry.class, AllayPackRegistry::new);

        api.implement("Allay");
    }

    private static void initRegistries() {
        // Item
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, EnchantmentType>())),
                r -> Registries.ENCHANTMENTS = r,
                new EnchantmentTypeRegistryPopulator()
        );
        Registries.VANILLA_ITEM_DATA = SimpleMappedRegistry.create(new VanillaItemDataLoader());
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<Identifier, ItemType<?>>()),
                r -> Registries.ITEMS = r,
                new ItemTypeRegistryPopulator()
        );
        Registries.ITEM_DEFINITIONS = SimpleRegistry.create(RegistryLoaders.empty(() -> {
            var itemDefinitions = new ArrayList<ItemDefinition>();
            for (var itemType : Registries.ITEMS.getContent().values()) {
                itemDefinitions.add(itemType.toNetworkDefinition());
            }
            return itemDefinitions;
        }));

        // BlockEntity
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<String, BlockEntityType<?>>()),
                r -> Registries.BLOCK_ENTITIES = r,
                new BlockEntityTypeRegistryPopulator()
        );

        // Block
        Registries.MATERIALS = SimpleMappedRegistry.create(new MaterialLoader());
        Registries.VANILLA_BLOCK_STATE_DATA = SimpleMappedRegistry.create(new VanillaBlockStateDataLoader());
        Registries.BLOCK_STATE_PALETTE = IntMappedRegistry.create(RegistryLoaders.empty(Int2ObjectOpenHashMap::new));
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<Identifier, BlockType<?>>()),
                r -> Registries.BLOCKS = r,
                new BlockTypeRegistryPopulator()
        );
        Registries.BLOCK_DEFINITIONS = SimpleRegistry.create(RegistryLoaders.empty(() -> {
            List<BlockDefinition> blockDefinitions = new ArrayList<>();
            for (var blockType : Registries.BLOCKS.getContent().values()) {
                blockType.getAllStates().forEach(state -> blockDefinitions.add(state.toNetworkBlockDefinition()));
            }
            return blockDefinitions;
        }));

        // Entity
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, EffectType>())),
                r -> Registries.EFFECTS = r,
                new EffectTypeRegistryPopulator()
        );
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<Identifier, EntityType<?>>()),
                r -> Registries.ENTITIES = r,
                new EntityTypeRegistryPopulator()
        );
        Registries.ENTITY_IDENTIFIERS = SimpleRegistry.create("entity_identifiers.nbt", RegistryLoaders.NBT);

        // World
        Registries.WORLD_STORAGE_FACTORIES = SimpleMappedRegistry.create(new WorldStorageFactoryRegistryLoader());
        Registries.WORLD_GENERATOR_FACTORIES = SimpleMappedRegistry.create(new WorldGeneratorFactoryRegistryLoader());

        // Creative Item Registry
        Registries.CREATIVE_ITEMS = IntMappedRegistry.create(new CreativeItemRegistryLoader());
        Registries.CREATIVE_ITEM_NETWORK_CONTENT = SimpleRegistry.create(RegistryLoaders.empty(() -> Registries.CREATIVE_ITEMS.getContent().values().stream().map(ItemStack::toNetworkItemData).toArray(ItemData[]::new)));
    }

    @VisibleForTesting
    public static void initI18n() {
        if (I18n.get() != null) return;
        AllayAPI.getInstance().bindI18n(new AllayI18n(new AllayI18nLoader(), Server.SETTINGS.genericSettings().language()));
    }
}
