package org.allaymc.api.pack;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.registry.MappedRegistry;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Allay Project 28/01/2024
 *
 * @author IWareQ
 */
public interface PackRegistry extends MappedRegistry<UUID, Pack, Map<UUID, Pack>> {

    ApiInstanceHolder<PackRegistry> REGISTRY = ApiInstanceHolder.of();

    static PackRegistry getRegistry() {
        return REGISTRY.get();
    }

    void loadResourcePacks(Path path);

    void registerLoaderFactory(PackLoader.Factory factory);

    @UnmodifiableView
    List<PackLoader.Factory> getLoadersFactory();

    void registerPackFactory(ResourcePackType packType, Pack.Factory factory);

    @UnmodifiableView
    Map<ResourcePackType, Pack.Factory> getPacksFactory();

    List<ResourcePackStackPacket.Entry> getEncodedResourcePacks();
}
