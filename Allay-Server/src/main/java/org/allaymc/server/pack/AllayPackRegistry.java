package org.allaymc.server.pack;

import com.google.common.base.Preconditions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.pack.PackRegistry;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.server.pack.defaults.ResourcePack;
import org.allaymc.server.pack.loader.ZipPackLoader;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Allay Project 28/01/2024
 *
 * @author IWareQ
 */
@Slf4j
public class AllayPackRegistry extends SimpleMappedRegistry<UUID, Pack, Map<UUID, Pack>> implements PackRegistry {

    private final List<PackLoader.Factory> packLoaders = new ArrayList<>();
    private final Map<ResourcePackType, Pack.Factory> packFactories = new EnumMap<>(ResourcePackType.class);

    public AllayPackRegistry() {
        super(null, i -> new HashMap<>());
        this.init();
    }

    @SneakyThrows
    private void init() {
        var resourcePacksPath = Path.of("resourcepacks");
        Files.createDirectories(resourcePacksPath);

        this.registerLoaderFactory(ZipPackLoader.FACTORY);
        // todo: more pack loaders

        this.registerPackFactory(ResourcePackType.RESOURCES, ResourcePack.FACTORY);
        // todo: more pack factories

        this.loadResourcePacks(resourcePacksPath);
    }

    @Override
    public void loadResourcePacks(Path path) {
        Preconditions.checkNotNull(path, "directoryPath");
        Preconditions.checkArgument(Files.isDirectory(path), "%s is not a directory", path);

        // find loaders
        List<PackLoader> foundedLoaders = new ArrayList<>();
        try (var stream = Files.newDirectoryStream(path)) {
            for (var entry : stream) {
                var loader = this.findLoader(entry);
                if (loader == null) continue;
                foundedLoaders.add(loader);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Load manifests
        Map<UUID, PackManifest> manifests = new HashMap<>();
        Map<UUID, PackLoader> loaders = new HashMap<>();
        for (var loader : foundedLoaders) {
            var manifest = PackManifest.load(loader);
            if (manifest == null || !manifest.isValid()) continue;

            var uuid = manifest.getHeader().getUuid();
            manifests.put(uuid, manifest);
            loaders.put(uuid, loader);
        }

        foundedLoaders.clear();

        for (var manifest : manifests.values()) {
            var loader = loaders.get(manifest.getHeader().getUuid());

            var module = manifest.getModules().getFirst();
            var factory = this.packFactories.get(module.getType() == null ? ResourcePackType.RESOURCES : module.getType());
            if (factory == null) {
                log.warn("Unsupported pack type {}", module.getType());
                continue;
            }

            var uuid = manifest.getHeader().getUuid();
            var pack = factory.create(loader, manifest);
            this.register(uuid, pack);

            // prepare for network
            loader.getNetworkPreparedFile();
        }

        log.info("Successfully loaded {} resource packs", this.getContent().size());
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


    @Override
    public void registerLoaderFactory(PackLoader.Factory factory) {
        this.packLoaders.add(factory);
    }

    @Override
    public @UnmodifiableView List<PackLoader.Factory> getLoadersFactory() {
        return Collections.unmodifiableList(this.packLoaders);
    }

    @Override
    public void registerPackFactory(ResourcePackType packType, Pack.Factory factory) {
        this.packFactories.put(packType, factory);
    }

    @Override
    public @UnmodifiableView Map<ResourcePackType, Pack.Factory> getPacksFactory() {
        return Collections.unmodifiableMap(this.packFactories);
    }

    @Override
    public List<ResourcePackStackPacket.Entry> getEncodedResourcePacks() {
        List<ResourcePackStackPacket.Entry> result = new ArrayList<>();
        for (var pack : this.getContent().values()) {
            if (pack.getType() == ResourcePackType.RESOURCES)
                result.add(new ResourcePackStackPacket.Entry(
                        pack.getId().toString(),
                        pack.getStringVersion(),
                        "" // todo: sub pack name
                ));
        }

        return result;
    }
}
