package org.allaymc.server.pack.defaults;

import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;

/**
 * {@code BehaviorPack} represents a loaded behavior-data pack for registry and
 * protocol consumers. Its factory retains the loader and manifest through the
 * base pack implementation, which stores archive bytes for transfer. Owners
 * close the pack when its loader resources are no longer needed.
 *
 * @author daoge_cmd
 */
public class BehaviorPack extends Pack {

    /**
     * {@code FACTORY} lets the pack registry construct behavior packs for
     * manifests whose selected module declares the data category.
     */
    public static final Pack.Factory FACTORY = BehaviorPack::new;

    /**
     * Creates a behavior pack from an open loader and parsed manifest.
     * The base constructor reads archive bytes and retains loader ownership;
     * this constructor obtains the encryption key directly from the loader.
     */
    private BehaviorPack(PackLoader loader, PackManifest manifest) {
        super(loader, manifest, loader.getContentKey());
    }

    /**
     * Returns the data module category represented by behavior packs.
     */
    @Override
    public Type getType() {
        return Type.DATA;
    }

    /**
     * Returns the fixed diagnostic label for this behavior pack implementation.
     */
    @Override
    public String toString() {
        return "BehaviorPack{}";
    }
}
