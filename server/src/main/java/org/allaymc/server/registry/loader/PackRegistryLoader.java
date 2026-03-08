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
 * @author daoge_cmd
 */
@Slf4j
public class PackRegistryLoader implements RegistryLoader<Void, Map<UUID, Pack>> {

    private static final Path NORMAL_PACKS_PATH = Path.of("resource_packs");
    private static final Set<Path> PACKS_PATHS = Set.of(
            // Normal resource packs path
            NORMAL_PACKS_PATH,
            // In-plugin resource packs path
            DefaultPluginSource.DEFAULT_PLUGIN_FOLDER
    );
    private static final List<String> EXCLUDED_FORMATS = List.of(".tmp", ".key", ".bak");

    private final List<PackLoader.Factory> packLoaders = new ArrayList<>();
    private final Map<Pack.Type, Pack.Factory> packFactories = new EnumMap<>(Pack.Type.class);

    public PackRegistryLoader() {
        init();
    }

    @SneakyThrows
    @Override
    public Map<UUID, Pack> load(Void unused) {
        log.info(I18n.get().tr(TrKeys.ALLAY_PACK_LOADING));
        var packs = new HashMap<UUID, Pack>();

        Preconditions.checkNotNull(PACKS_PATHS, "paths");
        for (var path : PACKS_PATHS) {
            Preconditions.checkArgument(Files.isDirectory(path), "%s is not a directory", path);
        }

        // find loaders
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

        // Load manifests
        Map<PackLoader, PackManifest> loader2manifest = new HashMap<>();
        for (var loader : foundedLoaders) {
            var manifest = PackManifest.load(loader);
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

    @SneakyThrows
    private void encryptPacks() {
        // Only encrypt packs in the normal packs path
        try (var stream = Files.newDirectoryStream(NORMAL_PACKS_PATH)) {
            for (var zipPack : stream) {
                if (!PackUtils.isZipPack(zipPack)) continue;

                var keyPath = NORMAL_PACKS_PATH.resolve(zipPack.getFileName().toString() + ".key");
                if (Files.exists(keyPath)) continue;

                log.info(I18n.get().tr(TrKeys.ALLAY_PACK_ENCRYPTING, zipPack.getFileName()));
                var backupPath = NORMAL_PACKS_PATH.resolve(zipPack.getFileName().toString() + ".bak");
                Files.copy(zipPack, backupPath, StandardCopyOption.REPLACE_EXISTING);

                var tmpPath = NORMAL_PACKS_PATH.resolve(zipPack.getFileName().toString() + ".tmp");
                Files.deleteIfExists(tmpPath);

                String key;
                try {
                    Files.move(zipPack, tmpPath);
                    key = PackEncryptor.encrypt(tmpPath, zipPack);
                } finally {
                    Files.delete(tmpPath);
                }

                Files.writeString(keyPath, key);
                log.info(I18n.get().tr(TrKeys.ALLAY_PACK_ENCRYPTED, zipPack.getFileName(), key));
            }
        }
    }

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

    private boolean isExcludedFormat(Path path) {
        var pathStr = path.toString();
        return EXCLUDED_FORMATS.stream().anyMatch(pathStr::endsWith);
    }

    public void registerLoaderFactory(PackLoader.Factory factory) {
        this.packLoaders.add(factory);
    }

    public void registerPackFactory(Pack.Type packType, Pack.Factory factory) {
        this.packFactories.put(packType, factory);
    }
}
