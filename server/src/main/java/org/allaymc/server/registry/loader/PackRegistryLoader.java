package org.allaymc.server.registry.loader;

import com.google.common.base.Preconditions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.server.AllayServer;
import org.allaymc.server.pack.PackEncryptor;
import org.allaymc.server.pack.PackUtils;
import org.allaymc.server.pack.defaults.BehaviorPack;
import org.allaymc.server.pack.defaults.ResourcePack;
import org.allaymc.server.pack.loader.InPluginPackLoader;
import org.allaymc.server.pack.loader.ZipPackLoader;
import org.allaymc.server.plugin.DefaultPluginSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * {@code PackRegistryLoader} discovers packs from the resource-pack and plugin directories and
 * constructs registered pack implementations from valid manifests. Loader factories are tried
 * in insertion order, while pack factories are selected by the first manifest module's type.
 * Pack UUIDs index the result, so duplicate UUIDs replace earlier entries in an unspecified
 * discovery order.
 *
 * <p>
 * Construction prepares directories and optionally encrypts ordinary ZIP packs before
 * discovery. Encryption first preserves a backup, moves the source aside, writes the encrypted
 * replacement, and finally writes its key sidecar. The temporary input is deleted even when
 * encryption fails, and no automatic rollback restores the original path; the backup therefore
 * remains the recovery source for a failed conversion.
 *
 * @author daoge_cmd
 */
@Slf4j
public class PackRegistryLoader implements RegistryLoader<Void, Map<UUID, Pack>> {

    /**
     * {@code NORMAL_PACKS_PATH} selects the directory for standalone pack discovery and automatic
     * encryption. Plugin-directory packs are discovered separately and do not enter this
     * encryption pass.
     */
    private static final Path NORMAL_PACKS_PATH = Path.of("resource_packs");
    /**
     * {@code PACKS_PATHS} contains the standalone and plugin discovery directories. The set does
     * not
     * establish a discovery priority, so callers cannot use directory ordering to resolve
     * duplicate UUIDs.
     */
    private static final Set<Path> PACKS_PATHS = Set.of(
            // Normal resource packs path
            NORMAL_PACKS_PATH,
            // In-plugin resource packs path
            DefaultPluginSource.DEFAULT_PLUGIN_FOLDER
    );
    /**
     * {@code EXCLUDED_FORMATS} excludes encryption temporary files, key sidecars, and backups from
     * pack discovery by matching their path-string suffixes before any loader factory is
     * consulted.
     */
    private static final List<String> EXCLUDED_FORMATS = List.of(".tmp", ".key", ".bak");

    // packLoaders controls discovery precedence and packFactories controls construction by
    // module type. Both are configured during initialization and read by later load calls.
    /**
     * Ordered pack-loader factory list.
     */
    private final List<PackLoader.Factory> packLoaders = new ArrayList<>();
    /**
     * Module-type pack construction factories.
     */
    private final Map<Pack.Type, Pack.Factory> packFactories = new EnumMap<>(Pack.Type.class);

    /**
     * Creates the loader and prepares its discovery directories and built-in factories. Server
     * resource-pack settings must be available because construction can encrypt ordinary ZIP packs
     * and write their backup and key files.
     */
    public PackRegistryLoader() {
        init();
    }

    /**
     * Discovers supported packs in the configured directories and returns a mutable UUID map.
     * Invalid manifests and unsupported module types are skipped; duplicate UUIDs overwrite
     * earlier
     * results without a defined directory priority. Other loader or factory failures abort loading
     * without undoing prior external side effects.
     */
    @SneakyThrows
    @Override
    public Map<UUID, Pack> load(Void unused) {
        log.info(I18n.get().tr(TrKeys.ALLAY_PACK_LOADING));
        var packs = new HashMap<UUID, Pack>();

        Preconditions.checkNotNull(PACKS_PATHS, "paths");
        for (var path : PACKS_PATHS) {
            Preconditions.checkArgument(Files.isDirectory(path), "%s is not a directory", path);
        }

        List<PackLoader> foundedLoaders = new ArrayList<>();
        for (var path : PACKS_PATHS) {
            try (var stream = Files.newDirectoryStream(path)) {
                for (var entry : stream) {
                    if (isExcludedFormat(entry)) {
                        continue;
                    }

                    var loader = this.findLoader(entry);
                    if (loader == null) {
                        continue;
                    }

                    foundedLoaders.add(loader);
                }
            }
        }

        Map<PackLoader, PackManifest> loader2manifest = new HashMap<>();
        for (var loader : foundedLoaders) {
            var manifest = PackManifest.load(loader);
            // Invalid manifests cannot identify a usable pack. Omit those candidates while
            // allowing the remaining discovered loaders to contribute valid packs to the result.
            if (manifest == null || !manifest.isValid()) {
                continue;
            }

            log.info(I18n.get().tr(TrKeys.ALLAY_PACK_LOADING_ENTRY, manifest.getHeader().getName()));
            loader2manifest.put(loader, manifest);
        }

        foundedLoaders.clear();

        loader2manifest.forEach((loader, manifest) -> {
            var module = manifest.getModules().getFirst();
            var factory = this.packFactories.get(module.getType());
            if (factory == null) {
                // No factory can construct this module type. Skip its manifest rather than
                // indexing a pack with no implementation, while retaining other supported packs.
                log.warn("Unsupported pack type {}", module.getType());
                return;
            }

            var uuid = manifest.getHeader().getUuid();
            var pack = factory.create(loader, manifest);
            packs.put(uuid, pack);

            log.info(I18n.get().tr(TrKeys.ALLAY_PACK_LOADED_ENTRY, pack.getName()));
        });
        log.info(I18n.get().tr(TrKeys.ALLAY_PACK_LOADED, packs.size()));
        return packs;
    }

    /**
     * Creates missing discovery directories, applies configured automatic encryption, and installs
     * the built-in loader and pack factories. Encryption finishes before discovery factories are
     * registered, so an encryption failure aborts construction at that stage.
     */
    @SneakyThrows
    private void init() {
        for (var path : PACKS_PATHS) {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        }
        if (AllayServer.getSettings().resourcePackSettings().autoEncryptPacks()) {
            encryptPacks();
        }
        this.registerLoaderFactory(ZipPackLoader.FACTORY);
        this.registerLoaderFactory(InPluginPackLoader.FACTORY);
        this.registerPackFactory(Pack.Type.RESOURCES, ResourcePack.FACTORY);
        this.registerPackFactory(Pack.Type.DATA, BehaviorPack.FACTORY);
    }

    /**
     * Encrypts standalone ZIP packs that have no key sidecar. Each source is copied to a backup
     * before its original path is reused for encrypted output; plugin-contained packs do not
     * participate in this pass.
     *
     * <p>
     * The source then moves to a temporary input that is deleted in {@code finally}, including
     * after encryption failure. The key is written only after encryption and cleanup succeed.
     * Failure does not restore the source automatically, so the backup can be needed even when
     * the output or key file is incomplete or absent.
     */
    @SneakyThrows
    private void encryptPacks() {
        // Plugin archives also contain plugin classes and metadata. Restrict this rewrite
        // pass to standalone ZIP packs so discovery does not replace plugin archive contents.
        try (var stream = Files.newDirectoryStream(NORMAL_PACKS_PATH)) {
            for (var zipPack : stream) {
                if (!PackUtils.isZipPack(zipPack)) continue;

                var keyPath = NORMAL_PACKS_PATH.resolve(zipPack.getFileName().toString() + ".key");
                if (Files.exists(keyPath)) continue;

                log.info(I18n.get().tr(TrKeys.ALLAY_PACK_ENCRYPTING, zipPack.getFileName()));
                var backupPath = NORMAL_PACKS_PATH.resolve(zipPack.getFileName().toString() + ".bak");
                // Preserve the original archive before moving its path to temporary storage.
                // Encryption and key-file writes do not roll back this sequence on failure.
                Files.copy(zipPack, backupPath, StandardCopyOption.REPLACE_EXISTING);

                var tmpPath = NORMAL_PACKS_PATH.resolve(zipPack.getFileName().toString() + ".tmp");
                Files.deleteIfExists(tmpPath);

                String key;
                try {
                    Files.move(zipPack, tmpPath);
                    key = PackEncryptor.encrypt(tmpPath, zipPack);
                } finally {
                    // The temporary archive is only encryption input. Remove it on both
                    // outcomes; failed encryption leaves the backup as the retained original.
                    Files.delete(tmpPath);
                }

                Files.writeString(keyPath, key);
                log.info(I18n.get().tr(TrKeys.ALLAY_PACK_ENCRYPTED, zipPack.getFileName(), key));
            }
        }
    }

    /**
     * Creates a loader using the first registered factory that accepts {@code path}. A path
     * accepted by no factory returns {@code null}; factory order therefore determines which loader
     * handles overlapping supported formats.
     */
    private PackLoader findLoader(Path path) throws IOException {
        PackLoader loader = null;
        for (var factory : this.packLoaders) {
            if (factory.canLoad(path)) {
                loader = factory.create(path);
                break;
            }
        }

        return loader;
    }

    /**
     * Returns whether the path ends with an excluded sidecar or temporary suffix.
     */
    private boolean isExcludedFormat(Path path) {
        var pathStr = path.toString();
        return EXCLUDED_FORMATS.stream().anyMatch(pathStr::endsWith);
    }

    /**
     * Appends a loader factory after existing discovery candidates. Discovery tries factories in
     * this order and stops at its first match, so callers configure precedence before starting
     * concurrent load operations.
     */
    public void registerLoaderFactory(PackLoader.Factory factory) {
        this.packLoaders.add(factory);
    }

    /**
     * Sets the construction factory for {@code packType}, replacing any prior mapping. Callers
     * configure factories before concurrent loading because this mutable enum map does not
     * coordinate registration with pack creation.
     */
    public void registerPackFactory(Pack.Type packType, Pack.Factory factory) {
        this.packFactories.put(packType, factory);
    }
}
