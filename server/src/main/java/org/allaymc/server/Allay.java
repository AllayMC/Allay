package org.allaymc.server;

import io.netty.util.ResourceLeakDetector;
import io.sentry.Sentry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permission;
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.bossbar.AllayBossBar;
import org.allaymc.server.command.selector.AllayEntitySelectorAPI;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.extension.ExtensionManager;
import org.allaymc.server.gui.Dashboard;
import org.allaymc.server.item.creative.AllayCreativeItemRegistry;
import org.allaymc.server.message.AllayI18n;
import org.allaymc.server.message.AllayI18nLoader;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.pdc.AllayPersistentDataTypeRegistry;
import org.allaymc.server.registry.AllayCommandRegistry;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.registry.loader.*;
import org.allaymc.server.registry.populator.*;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.AllayNBTIO;
import org.allaymc.server.utils.DynamicURLClassLoader;
import org.allaymc.server.utils.GitProperties;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.jetbrains.annotations.VisibleForTesting;

import java.nio.file.Path;
import java.util.HashMap;

import static java.lang.Thread.sleep;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class Allay {

    public static final DynamicURLClassLoader EXTRA_RESOURCE_CLASS_LOADER = new DynamicURLClassLoader(Allay.class.getClassLoader());
    public static final ExtensionManager EXTENSION_MANAGER = new ExtensionManager(Path.of("extensions"));

    public static Dashboard DASHBOARD;

    public static void main(String[] args) throws InterruptedException {
        long initialTime = System.currentTimeMillis();
        if (GitProperties.isDevBuild() && !AllayServer.getSettings().genericSettings().forceEnableSentry()) {
            // Enable sentry only in non-dev build
            Sentry.close();
        }
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);
        // Disable scientific notation in joml
        System.setProperty("joml.format", "false");
        // Enable async logging
        System.setProperty("log4j2.contextSelector", AsyncLoggerContextSelector.class.getName());

        initI18n();
        EXTENSION_MANAGER.loadExtensions(args);

        // Check if the environment is headless
        if (isHeadless()) {
            AllayServer.getSettings().genericSettings().enableGui(false);
        }
        if (AllayServer.getSettings().genericSettings().enableGui()) {
            try {
                DASHBOARD = Dashboard.getInstance();
            } catch (Throwable t) {
                log.error("Cannot init Dashboard!", t);
                AllayServer.getSettings().genericSettings().enableGui(false);
            }
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_SERVER_STARTING));

        try {
            initAllay();
        } catch (Exception e) {
            log.error("Cannot init Allay API!", e);
            if (DASHBOARD != null) {
                sleep(5000);
            }
            System.exit(1);
        }

        try {
            ((AllayServer) Server.getInstance()).start(initialTime);
        } catch (Throwable t) {
            log.error("Error while starting the server!", t);
            // The server may not be initialized correctly
            // So we can't call Server::shutdown() to stop the server
            if (DASHBOARD != null) {
                sleep(5000);
            }
            System.exit(1);
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_SERVER_STOPPED));
        // Server has been shutdown
        // Call System.exit(0) to stop other non-daemon threads
        System.exit(0);
    }

    private static boolean isHeadless() {
        try {
            var graphicsEnv = Class.forName("java.awt.GraphicsEnvironment");
            var isHeadless = graphicsEnv.getDeclaredMethod("isHeadless");
            return (boolean) isHeadless.invoke(null);
        } catch (Exception ignore) {
        }
        return true;
    }

    /**
     * NOTICE: The i18n implementation must be registered before initializing allay,
     * which means that you should call initI18n() before call initAllay()!
     */
    @VisibleForTesting
    public static void initAllay() {
        initAllayAPI();
        initRegistries();
    }

    private static void initAllayAPI() {
        var api = AllayAPI.getInstance();
        if (api.isImplemented()) return;

        // Common
        api.bind(Server.class, AllayServer::getInstance);
        api.bind(Scheduler.Factory.class, () -> AllayScheduler::new);
        api.bind(EventBus.Factory.class, () -> AllayEventBus::new);

        // Command
        api.bind(EntitySelectorAPI.class, AllayEntitySelectorAPI::new);
        api.bind(CommandTree.Factory.class, () -> AllayCommandTree::create);
        api.bind(CommandNodeFactory.class, AllayCommandNodeFactory::new);

        // Misc
        api.bind(BossBar.Factory.class, () -> AllayBossBar::new);
        api.bind(NBTIO.class, AllayNBTIO::new);

        api.implement("allay", GitProperties.isDevBuild());

        var versionStr = ProtocolInfo.getLowestCodec().getMinecraftVersion() + " - " + ProtocolInfo.getLatestCodec().getMinecraftVersion();
        var protocolStr = ProtocolInfo.getLowestCodec().getProtocolVersion() + " - " + ProtocolInfo.getLatestCodec().getProtocolVersion();
        log.info(I18n.get().tr(
                TrKeys.ALLAY_COMMAND_VERSION_OUTPUT,
                AllayAPI.getInstance().getCoreName(), GitProperties.getBuildVersion(),
                GitProperties.getBuildApiVersion(), versionStr, protocolStr
        ));
        if (AllayAPI.getInstance().isDevBuild()) {
            log.warn(I18n.get().tr(TrKeys.ALLAY_SERVER_IS_DEV_VERSION));
        }
    }

    private static void initRegistries() {
        // Item
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, EnchantmentType>())),
                r -> Registries.ENCHANTMENTS = r,
                new EnchantmentTypeRegistryPopulator()
        );
        InternalRegistries.ITEM_DATA = SimpleMappedRegistry.create(new ItemDataRegistryLoader());
        InternalRegistries.ITEM_TAGS = SimpleMappedRegistry.create(new ItemTagRegistryLoader());
        InternalRegistries.ITEM_DEFINITIONS = SimpleMappedRegistry.create(new ItemDefinitionRegistryLoader());
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
        InternalRegistries.BLOCK_STATE_DATA = SimpleMappedRegistry.create(new BlockStateDataRegistryLoader());
        InternalRegistries.BLOCK_TAGS = SimpleMappedRegistry.create(new BlockTagRegistryLoader());
        InternalRegistries.BLOCK_DEFAULT_STATE_HASHES = SimpleMappedRegistry.create(new BlockDefaultStateHashRegistryLoader());
        InternalRegistries.BLOCK_PROPERTY_PROCESSORS = SimpleMappedRegistry.create(new BlockPropertyProcessorRegistryLoader());
        Registries.BLOCK_STATE_PALETTE = SimpleMappedRegistry.create(RegistryLoaders.empty(Int2ObjectOpenHashMap::new));
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<Identifier, BlockType<?>>()),
                r -> Registries.BLOCKS = r,
                new BlockTypeRegistryPopulator()
        );

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

        // Biome
        InternalRegistries.BIOME_DATA = SimpleMappedRegistry.create(new BiomeDataRegistryLoader());
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, BiomeType>())),
                r -> Registries.BIOMES = r,
                new BiomeTypeRegistryPopulator()
        );

        // World
        Registries.WORLD_STORAGE_FACTORIES = SimpleMappedRegistry.create(new WorldStorageFactoryRegistryLoader());
        Registries.WORLD_GENERATOR_FACTORIES = SimpleMappedRegistry.create(new WorldGeneratorFactoryRegistryLoader());

        // Creative Item
        Registries.CREATIVE_ITEMS = new AllayCreativeItemRegistry();

        // Recipe
        Registries.RECIPES = SimpleMappedRegistry.create(new RecipeRegistryLoader());

        // Trim Data
        InternalRegistries.TRIM_PATTERNS = SimpleMappedRegistry.create(new TrimPatternRegistryLoader());
        InternalRegistries.TRIM_MATERIALS = SimpleMappedRegistry.create(new TrimMaterialRegistryLoader());

        // Pack
        Registries.PACKS = SimpleMappedRegistry.create(new PackRegistryLoader());

        // Permission
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<String, Permission>()),
                r -> Registries.PERMISSIONS = r,
                new PermissionRegistryPopulator()
        );
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<String, PermissionGroup>()),
                r -> Registries.PERMISSION_GROUPS = r,
                new PermissionGroupRegistryPopulator()
        );

        // Command
        Registries.COMMANDS = new AllayCommandRegistry();

        // Persistent Data Container (PDC)
        Registries.PERSISTENT_DATA_TYPES = new AllayPersistentDataTypeRegistry();
    }

    @VisibleForTesting
    public static void initI18n() {
        if (I18n.get() != null) {
            return;
        }

        AllayAPI.getInstance().bindI18n(new AllayI18n(new AllayI18nLoader(), AllayServer.getSettings().genericSettings().language()));
    }
}
