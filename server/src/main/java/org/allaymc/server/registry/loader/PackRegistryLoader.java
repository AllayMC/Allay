package org.allaymc.server.registry.loader;

import com.google.common.base.Preconditions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.server.Server;
import org.allaymc.server.pack.PackEncryptor;
import org.allaymc.server.pack.PackUtils;
import org.allaymc.server.pack.defaults.ResourcePack;
import org.allaymc.server.pack.loader.ZipPackLoader;

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

    private static final Path PACKS_PATH = Path.of("resource_packs");
    private static final List<String> EXCLUDED_FORMATS = List.of(".tmp", ".key", ".bak");

    private final List<PackLoader.Factory> packLoaders = new ArrayList<>();
    private final Map<Pack.Type, Pack.Factory> packFactories = new EnumMap<>(Pack.Type.class);

    public PackRegistryLoader() {
        init();
    }

    @SneakyThrows
    @Override
    public Map<UUID, Pack> load(Void unused) {
        log.info(I18n.get().tr(TrKeys.A_PACK_LOADING));
        var packs = new HashMap<UUID, Pack>();

        Preconditions.checkNotNull(PACKS_PATH, "path");
        Preconditions.checkArgument(Files.isDirectory(PACKS_PATH), "%s is not a directory", PACKS_PATH);

        // find loaders
        List<PackLoader> foundedLoaders = new ArrayList<>();
        try (var stream = Files.newDirectoryStream(PACKS_PATH)) {
            for (var entry : stream) {
                if (isExcludedFormat(entry)) continue;

                var loader = this.findLoader(entry);
                if (loader == null) continue;

                foundedLoaders.add(loader);
            }
        }

        // Load manifests
        Map<PackLoader, PackManifest> loader2manifest = new HashMap<>();
        for (var loader : foundedLoaders) {
            var manifest = PackManifest.load(loader);
            if (manifest == null || !manifest.isValid()) continue;

            log.info(I18n.get().tr(TrKeys.A_PACK_LOADING_ENTRY, manifest.getHeader().getName()));
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

            // prepare for network
            loader.getNetworkPreparedFile();
            log.info(I18n.get().tr(TrKeys.A_PACK_LOADED_ENTRY, pack.getName()));
        });
        log.info(I18n.get().tr(TrKeys.A_PACK_LOADED, packs.size()));
        return packs;
    }

    @SneakyThrows
    private void init() {
        if (Files.exists(PACKS_PATH) && Server.SETTINGS.resourcePackSettings().autoEncryptPacks()) {
            encryptPacks();
        } else {
            Files.createDirectories(PACKS_PATH);
        }
        this.registerLoaderFactory(ZipPackLoader.FACTORY);
        // todo: more pack loaders
        this.registerPackFactory(Pack.Type.RESOURCES, ResourcePack.FACTORY);
        // todo: more pack factories
    }

    @SneakyThrows
    private void encryptPacks() {
        log.info(I18n.get().tr(TrKeys.A_PACK_AUTOENCRYPT_ENABLED));
        try (var stream = Files.newDirectoryStream(PACKS_PATH)) {
            for (var zipPack : stream) {
                if (!PackUtils.isZipPack(zipPack)) continue;

                var keyPath = PACKS_PATH.resolve(zipPack.getFileName().toString() + ".key");
                if (Files.exists(keyPath)) continue;

                log.info(I18n.get().tr(TrKeys.A_PACK_ENCRYPTING, zipPack.getFileName()));
                var backupPath = PACKS_PATH.resolve(zipPack.getFileName().toString() + ".bak");
                Files.copy(zipPack, backupPath, StandardCopyOption.REPLACE_EXISTING);

                var tmpPath = PACKS_PATH.resolve(zipPack.getFileName().toString() + ".tmp");
                Files.deleteIfExists(tmpPath);

                String key;
                try {
                    Files.move(zipPack, tmpPath);
                    key = PackEncryptor.encrypt(tmpPath, zipPack);
                } finally {
                    Files.delete(tmpPath);
                }

                Files.writeString(keyPath, key);
                log.info(I18n.get().tr(TrKeys.A_PACK_ENCRYPTED, zipPack.getFileName(), key));
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

        if (loader == null) log.warn("Could not load '{}' due to format not recognized", path);
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
