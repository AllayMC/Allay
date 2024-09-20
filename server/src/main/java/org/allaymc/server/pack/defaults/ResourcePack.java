package org.allaymc.server.pack.defaults;

import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;

/**
 * @author IWareQ
 */
public class ResourcePack extends Pack {

    public static final Pack.Factory FACTORY = ResourcePack::new;

    private ResourcePack(PackLoader loader, PackManifest manifest) {
        super(loader, manifest, loader.findContentKey());
    }

    @Override
    public Type getType() {
        return Type.RESOURCES;
    }

    @Override
    public String toString() {
        return "ResourcePack{}";
    }
}
