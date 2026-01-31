package org.allaymc.server.pack.defaults;

import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;

/**
 * @author daoge_cmd
 */
public class BehaviorPack extends Pack {

    public static final Pack.Factory FACTORY = BehaviorPack::new;

    private BehaviorPack(PackLoader loader, PackManifest manifest) {
        super(loader, manifest, loader.getContentKey());
    }

    @Override
    public Type getType() {
        return Type.DATA;
    }

    @Override
    public String toString() {
        return "BehaviorPack{}";
    }
}
