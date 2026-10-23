package org.allaymc.server;

import io.netty.util.ResourceLeakDetector;
import io.sentry.Sentry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.feature.WorldFeature;
import org.allaymc.server.command.selector.AllayEntitySelectorAPI;
import org.allaymc.server.command.tree.AllayCommandNodeFactory;
import org.allaymc.server.command.tree.AllayCommandTree;
import org.allaymc.server.container.AllayFakeContainerFactory;
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
import org.allaymc.server.world.biome.CustomBiomeIdAllocator;
import org.allaymc.server.world.dimension.CustomDimensionIdAllocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.jetbrains.annotations.VisibleForTesting;

import java.nio.file.Path;
import java.util.HashMap;

import static java.lang.Thread.sleep;

/**
 * {@code Allay} bootstraps server-wide APIs, registries, extensions, and the
 * executable process.
 * Internationalization is installed before extensions and registry population
 * so bootstrap failures can use translated output. Extensions can contribute
 * resources before the registries consume them, and API implementations are
 * published before constructing registry entries that use those services.
 *
 * <p>
 * {@code initAllay} marks completion only after all registries return;
 * partial failures leave prior publications intact and may be retried without
 * rebinding an already implemented API. The main entry additionally manages
 * optional GUI setup, process-wide library settings, and explicit process exit.
 * A fatal startup failure exits without assuming that a partially initialized
 * server can complete its normal shutdown sequence.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class Allay {

    /**
     * {@code EXTRA_RESOURCE_CLASS_LOADER} supplies shared extension resources.
     * Extensions add JARs before registry loaders read those resources, and
     * bootstrap retains the loader for later lookups.
     */
    public static final DynamicURLClassLoader EXTRA_RESOURCE_CLASS_LOADER = new DynamicURLClassLoader(Allay.class.getClassLoader());
    /**
     * {@code EXTENSION_MANAGER} discovers bootstrap extensions beneath the
     * working directory's {@code extensions} folder and adds their JARs to
     * {@code EXTRA_RESOURCE_CLASS_LOADER}.
     */
    public static final ExtensionManager EXTENSION_MANAGER = new ExtensionManager(Path.of("extensions"));

    /**
     * {@code DASHBOARD} holds the optional dashboard created during {@code main}.
     * It remains {@code null} when GUI startup is disabled or fails before assignment.
     */
    public static Dashboard DASHBOARD;
    /**
     * {@code initialized} records completion under the class monitor in
     * {@code initAllay}. It becomes {@code true} only after API binding and registry
     * initialization return successfully.
     */
    private static boolean initialized;

    /**
     * Starts the executable server process and exits when its main loop returns.
     * Internationalization and extensions precede API and registry initialization,
     * followed by server startup on this thread. Headless or failed GUI setup
     * disables the dashboard while allowing normal startup to continue. Caught
     * API or server startup failures close logging and exit with status one;
     * successful return exits with status zero to stop remaining non-daemon
     * threads. Earlier uncaught setup failures and interruption of error-display
     * delays still propagate to the process caller.
     */
    public static void main(String[] args) throws InterruptedException {
        long initialTime = System.currentTimeMillis();
        if (GitProperties.isDevBuild() && !AllayServer.getSettings().genericSettings().forceEnableSentry()) {
            // Development builds close the error-reporting client unless the
            // explicit override keeps it enabled for investigating development failures.
            Sentry.close();
        }
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);
        // Use ordinary decimal formatting in JOML output.
        System.setProperty("joml.format", "false");
        // Select asynchronous logger contexts before subsequent bootstrap logging.
        System.setProperty("log4j2.contextSelector", AsyncLoggerContextSelector.class.getName());
        System.setProperty("bedrock.maxDecompressedBytes", String.valueOf(AllayServer.getSettings().networkSettings().maxDecompressedBytes()));

        initI18n();
        EXTENSION_MANAGER.loadExtensions(args);

        // Disable GUI construction when graphics are unavailable, preserving
        // command-line startup on servers without a usable desktop environment.
        if (isHeadless()) {
            AllayServer.getSettings().genericSettings().enableGui(false);
        }
        if (AllayServer.getSettings().genericSettings().enableGui()) {
            try {
                DASHBOARD = Dashboard.getInstance();
            } catch (Throwable t) {
                // A dashboard failure must not prevent console-only startup;
                // disable later GUI callbacks that would otherwise expect a usable dashboard.
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
                // Keep a visible dashboard open briefly so its startup error
                // can be read before process exit closes the logging system.
                sleep(5000);
            }
            LogManager.shutdown();
            System.exit(1);
        }

        try {
            ((AllayServer) Server.getInstance()).start(initialTime);
        } catch (Throwable t) {
            log.error("Error while starting the server!", t);
            // Startup may have left services only partially initialized, so
            // fatal failure exits directly rather than relying on normal server cleanup.
            if (DASHBOARD != null) {
                // Leave the failed server startup visible in the dashboard
                // briefly before terminating the process and discarding its interactive window.
                sleep(5000);
            }
            LogManager.shutdown();
            System.exit(1);
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_SERVER_STOPPED));
        // The main loop has returned, but background non-daemon threads may
        // remain; closing logging before explicit process exit preserves the final message.
        LogManager.shutdown();
        System.exit(0);
    }

    /**
     * Returns the graphics environment's headless result when AWT is accessible.
     * Reflection avoids a direct dependency on an available desktop module;
     * any lookup or invocation exception conservatively disables GUI startup.
     */
    private static boolean isHeadless() {
        try {
            var graphicsEnv = Class.forName("java.awt.GraphicsEnvironment");
            var isHeadless = graphicsEnv.getDeclaredMethod("isHeadless");
            return (boolean) isHeadless.invoke(null);
        } catch (Exception ignore) {
            // Missing or inaccessible desktop support cannot provide a reliable
            // graphics environment, so treat the process as headless and retain console startup.
        }
        return true;
    }

    /**
     * Initializes API bindings and registries once after internationalization is
     * available. The class monitor serializes callers and the completion flag
     * suppresses later successful repeats. The flag is set only after both
     * phases return, so failures leave prior bindings and registry publications
     * intact while allowing a later attempt. That attempt skips API binding
     * if it already completed, but reruns registry initialization; no rollback
     * restores the state that existed before a failed attempt.
     */
    @VisibleForTesting
    public static synchronized void initAllay() {
        if (initialized) {
            return;
        }
        // Registry construction can consume API holders and emit translated
        // messages, so publish implementations before registries and mark completion only afterward.
        initAllayAPI();
        initRegistries();
        initialized = true;
    }

    /**
     * Binds the default server implementations unless the API is already marked
     * implemented. Internationalization must have been installed first; binding
     * failures propagate, while a successful pass publishes services and logs
     * server, API, and supported protocol versions.
     */
    private static void initAllayAPI() {
        var api = AllayAPI.getInstance();
        if (api.isImplemented()) return;

        api.bind(Server.class, AllayServer::getInstance);
        api.bind(Scheduler.Factory.class, () -> AllayScheduler::new);
        api.bind(EventBus.Factory.class, () -> AllayEventBus::new);

        api.bind(EntitySelectorAPI.class, AllayEntitySelectorAPI::new);
        api.bind(CommandTree.Factory.class, () -> AllayCommandTree::create);
        api.bind(CommandNodeFactory.class, AllayCommandNodeFactory::new);

        api.bind(NBTIO.class, AllayNBTIO::new);
        api.bind(FakeContainerFactory.class, AllayFakeContainerFactory::new);

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

    /**
     * Constructs runtime registries and publishes their entries in bootstrap order.
     * API services and internationalization must already be available. Raw item,
     * block, and biome data precede the corresponding type population, and
     * custom biome and dimension ID allocation follows their populated registries.
     * Later registries consume earlier type and resource data. A failure leaves
     * already assigned global registry references intact, so this method does
     * not provide transactional initialization or restore previous registry contents.
     */
    private static void initRegistries() {
        // Load raw data before type population, and populate type registries
        // before later content registries resolve their referenced items, blocks, and dimensions.
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, EnchantmentType>())),
                r -> Registries.ENCHANTMENTS = r,
                new EnchantmentTypeRegistryPopulator()
        );
        InternalRegistries.ITEM_DATA = SimpleMappedRegistry.create(new ItemDataRegistryLoader());
        InternalRegistries.ITEM_TAGS = SimpleMappedRegistry.create(new ItemTagRegistryLoader());
        InternalRegistries.VANILLA_ITEM_DEFINITIONS = SimpleMappedRegistry.create(new VanillaItemDefinitionRegistryLoader());
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<Identifier, ItemType<?>>()),
                r -> Registries.ITEMS = r,
                new ItemTypeRegistryPopulator()
        );
        Registries.COMPOSTABLE_ITEMS = SimpleMappedRegistry.create(new CompostableItemRegistryLoader());

        Registries.FISHING_LOOTS = SimpleMappedRegistry.create(new FishingLootRegistryLoader());

        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<String, BlockEntityType<?>>()),
                r -> Registries.BLOCK_ENTITIES = r,
                new BlockEntityTypeRegistryPopulator()
        );

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

        InternalRegistries.BIOME_DATA = SimpleMappedRegistry.create(new BiomeDataRegistryLoader());
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, BiomeType>())),
                r -> Registries.BIOMES = r,
                new BiomeTypeRegistryPopulator()
        );
        CustomBiomeIdAllocator.init();
        DoubleKeyMappedRegistry.create(
                RegistryLoaders.empty(() -> new DoubleKeyMappedRegistry.MapPair<>(new Int2ObjectOpenHashMap<>(), new HashMap<Identifier, DimensionType>())),
                r -> Registries.DIMENSIONS = r,
                new DimensionTypeRegistryPopulator()
        );
        CustomDimensionIdAllocator.init();

        Registries.WORLD_STORAGE_FACTORIES = SimpleMappedRegistry.create(new WorldStorageFactoryRegistryLoader());
        Registries.WORLD_GENERATOR_FACTORIES = SimpleMappedRegistry.create(new WorldGeneratorFactoryRegistryLoader());
        SimpleMappedRegistry.create(
                RegistryLoaders.empty(() -> new HashMap<Identifier, WorldFeature>()),
                r -> Registries.WORLD_FEATURES = r,
                new WorldFeatureRegistryPopulator()
        );

        Registries.POI_TYPES = SimpleMappedRegistry.create(new PoiTypeRegistryLoader());

        Registries.CREATIVE_ITEMS = new AllayCreativeItemRegistry();

        Registries.RECIPES = SimpleMappedRegistry.create(new RecipeRegistryLoader());

        InternalRegistries.TRIM_PATTERNS = SimpleMappedRegistry.create(new TrimPatternRegistryLoader());
        InternalRegistries.TRIM_MATERIALS = SimpleMappedRegistry.create(new TrimMaterialRegistryLoader());

        Registries.PACKS = SimpleMappedRegistry.create(new PackRegistryLoader());

        Registries.COMMANDS = new AllayCommandRegistry();

        Registries.PERSISTENT_DATA_TYPES = new AllayPersistentDataTypeRegistry();

        Registries.DISPENSER_BEHAVIORS = SimpleMappedRegistry.create(new DispenserBehaviorRegistryLoader());
    }

    /**
     * Installs the default translation service when its API holder is empty.
     * Callers invoke this before registry bootstrap and serialize competing initialization;
     * the check and binding are separate operations rather than an atomic
     * selection between concurrent translation-service candidates.
     */
    @VisibleForTesting
    public static void initI18n() {
        if (I18n.get() != null) {
            return;
        }

        AllayAPI.getInstance().bindI18n(new AllayI18n(new AllayI18nLoader(), AllayServer.getSettings().genericSettings().language()));
    }
}
