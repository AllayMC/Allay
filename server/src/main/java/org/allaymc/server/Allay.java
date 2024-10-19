package org.allaymc.server;

import io.netty.util.ResourceLeakDetector;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.property.BlockPropertyProcessor;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;
import org.allaymc.api.registry.IntMappedRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.MissingImplementationException;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.bossbar.AllayBossBar;
import org.allaymc.server.command.selector.AllayEntitySelectorAPI;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.extension.ExtensionManager;
import org.allaymc.server.gui.Dashboard;
import org.allaymc.server.i18n.AllayI18n;
import org.allaymc.server.i18n.AllayI18nLoader;
import org.allaymc.server.perm.tree.AllayPermTree;
import org.allaymc.server.registry.AllayCommandRegistry;
import org.allaymc.server.registry.loader.*;
import org.allaymc.server.registry.populator.*;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.allaymc.server.utils.DynamicURLClassLoader;
import org.allaymc.server.utils.GitProperties;
import org.allaymc.server.world.generator.AllayWorldGenerator;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.jetbrains.annotations.VisibleForTesting;

import java.nio.file.Path;
import java.util.HashMap;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class Allay {

    public static final DynamicURLClassLoader EXTRA_RESOURCE_CLASS_LOADER = new DynamicURLClassLoader(Allay.class.getClassLoader());
    private static final ExtensionManager EXTENSION_MANAGER =new ExtensionManager(Path.of("extensions"));

    public static Dashboard DASHBOARD;

    public static void main(String[] args) {
        long initialTime = System.currentTimeMillis();
        ResourceLeakDetector.setLevel(Server.SETTINGS.networkSettings().resourceLeakDetectorLevel());
        // Disable scientific notation in joml
        System.setProperty("joml.format", "false");
        // Enable async logging
        System.setProperty("log4j2.contextSelector", AsyncLoggerContextSelector.class.getName());

        initI18n();
        EXTENSION_MANAGER.loadExtensions(args);

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
            ((AllayServer) Server.getInstance()).start(initialTime);
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
        api.bind(Server.class, AllayServer::getInstance);
        api.bind(Scheduler.Factory.class, () -> AllayScheduler::new);
        api.bind(EventBus.Factory.class, () -> AllayEventBus::new);

        // World
        api.bind(WorldGenerator.WorldGeneratorBuilderFactory.class, () -> AllayWorldGenerator::builder);

        // Perm
        api.bind(PermTree.Factory.class, () -> AllayPermTree::create);

        // Command
        api.bind(EntitySelectorAPI.class, AllayEntitySelectorAPI::new);
        api.bind(CommandTree.Factory.class, () -> AllayCommandTree::create);
        api.bind(CommandNodeFactory.class, AllayCommandNodeFactory::new);

        // Misc
        api.bind(BossBar.Factory.class, () -> AllayBossBar::new);

        api.implement("allay");
        log.info(I18n.get().tr(TrKeys.A_API_IMPLEMENTED, AllayAPI.getInstance().getCoreName(), GitProperties.getBranch() + "-" + GitProperties.getCommitIdAbbrev() + " " + GitProperties.getBuildVersion(), AllayAPI.API_VERSION));
    }

    private static void initRegistries() {
        // Item
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, EnchantmentType>())),
                r -> Registries.ENCHANTMENTS = r,
                new EnchantmentTypeRegistryPopulator()
        );
        Registries.ITEM_DATA = SimpleMappedRegistry.create(new ItemDataLoader());
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
        Registries.BLOCK_STATE_DATA = SimpleMappedRegistry.create(new BlockStateDataLoader());
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
