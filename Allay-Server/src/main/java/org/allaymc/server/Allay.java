package org.allaymc.server;

import io.netty.util.ResourceLeakDetector;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.property.BlockPropertyProcessor;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.component.interfaces.ComponentInjector;
import org.allaymc.api.datastruct.DynamicURLClassLoader;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;
import org.allaymc.api.registry.IntMappedRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.exception.MissingImplementationException;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.blockentity.type.AllayBlockEntityType;
import org.allaymc.server.command.selector.AllayEntitySelectorAPI;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.entity.type.AllayEntityType;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.gui.Dashboard;
import org.allaymc.server.i18n.AllayI18n;
import org.allaymc.server.i18n.AllayI18nLoader;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.perm.tree.AllayPermTree;
import org.allaymc.server.registry.AllayCommandRegistry;
import org.allaymc.server.registry.loader.*;
import org.allaymc.server.registry.populator.*;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.allaymc.server.world.generator.AllayWorldGenerator;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.HashMap;

@Slf4j
public final class Allay {

    public static final DynamicURLClassLoader EXTRA_RESOURCE_CLASS_LOADER = new DynamicURLClassLoader(Allay.class.getClassLoader());

    public static Dashboard DASHBOARD;

    public static void main(String[] args) {
        long initialTime = System.currentTimeMillis();
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);
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
            Server.getInstance().start(initialTime);
        } catch (Throwable t) {
            log.error("Error while starting the server!", t);
            // The server may not be initialized correctly
            // So we can't call Server::shutdown() to stop the server
            System.exit(1);
        }

        log.info(I18n.get().tr(TrKeys.A_SERVER_STOPPED));
        // Server has been shutdown
        // Call System.exit(0) to stop other non-daemon threads
        System.exit(0);
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
        api.bind(Scheduler.SchedulerFactory.class, () -> AllayScheduler::new);
        api.bind(EventBus.Factory.class, () -> AllayEventBus::new);

        // Item
        api.bind(ItemTypeBuilder.ItemTypeBuilderFactory.class, () -> AllayItemType::builder);

        // BlockEntity
        api.bind(BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory.class, () -> AllayBlockEntityType::builder);

        // Block
        api.bind(BlockTypeBuilder.BlockTypeBuilderFactory.class, () -> AllayBlockType::builder);

        // Entity
        api.bind(EntityTypeBuilder.EntityTypeBuilderFactory.class, () -> AllayEntityType::builder);

        // World
        api.bind(WorldGenerator.WorldGeneratorBuilderFactory.class, () -> AllayWorldGenerator::builder);

        // Perm
        api.bind(PermTree.PermTreeFactory.class, () -> AllayPermTree::create);

        // Command
        api.bind(EntitySelectorAPI.class, AllayEntitySelectorAPI::new);
        api.bind(CommandTree.CommandTreeFactory.class, () -> AllayCommandTree::create);
        api.bind(CommandNodeFactory.class, AllayCommandNodeFactory::new);

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
        BlockPropertyProcessor.init();

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

        // World
        Registries.WORLD_STORAGE_FACTORIES = SimpleMappedRegistry.create(new WorldStorageFactoryRegistryLoader());
        Registries.WORLD_GENERATOR_FACTORIES = SimpleMappedRegistry.create(new WorldGeneratorFactoryRegistryLoader());

        // Creative Item
        Registries.CREATIVE_ITEMS = IntMappedRegistry.create(new CreativeItemRegistryLoader());

        // Recipe
        Registries.RECIPES = IntMappedRegistry.create(new RecipeRegistryLoader());
        Registries.FURNACE_RECIPES = SimpleMappedRegistry.create(new FurnaceRecipeRegistryLoader());

        // Pack
        Registries.PACKS = SimpleMappedRegistry.create(new PackRegistryLoader());

        // Command
        Registries.COMMANDS = new AllayCommandRegistry();
    }

    @VisibleForTesting
    public static void initI18n() {
        if (I18n.get() != null) return;
        AllayAPI.getInstance().bindI18n(new AllayI18n(new AllayI18nLoader(), Server.SETTINGS.genericSettings().language()));
    }
}
