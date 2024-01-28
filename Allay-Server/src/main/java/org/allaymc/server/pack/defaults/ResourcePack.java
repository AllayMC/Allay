package org.allaymc.server.pack.defaults;

import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackType;

/**
 * Allay Project 28/01/2024
 *
 * @author IWareQ
 */
public class ResourcePack extends Pack {

    public static final Pack.Factory FACTORY = ResourcePack::new;

    private ResourcePack(PackLoader loader, PackManifest manifest) {
        super(loader, manifest);
    }

    @Override
    public ResourcePackType getType() {
        return ResourcePackType.RESOURCES;
    }

    @Override
    public String toString() {
        return "ResourcePack{}";
    }
}
