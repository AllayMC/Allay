package org.allaymc.api.pack;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.registry.AbstractMappedRegistry;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;
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
public interface PackRegistry extends AbstractMappedRegistry<UUID, Pack, Map<UUID, Pack>> {

    ApiInstanceHolder<PackRegistry> REGISTRY = ApiInstanceHolder.create();

    static PackRegistry getRegistry() {
        return REGISTRY.get();
    }

    void loadResourcePacks(Path path);

    ResourcePackStackPacket getPackStackPacket();

    ResourcePacksInfoPacket getPacksInfoPacket();

    void registerLoaderFactory(PackLoader.Factory factory);

    @UnmodifiableView
    List<PackLoader.Factory> getLoadersFactory();

    void registerPackFactory(Pack.Type packType, Pack.Factory factory);

    @UnmodifiableView
    Map<Pack.Type, Pack.Factory> getPacksFactory();
}
