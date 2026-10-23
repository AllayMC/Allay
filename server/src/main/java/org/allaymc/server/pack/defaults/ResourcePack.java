package org.allaymc.server.pack.defaults;

import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.api.pack.PackManifest;

/**
 * {@code ResourcePack} represents a loaded client-resource pack for registry and
 * protocol consumers. Its factory delegates archive storage and identity to the
 * base pack implementation. The pack retains its loader until the owner closes
 * it after manifest and file access are no longer needed.
 *
 * @author IWareQ
 */
public class ResourcePack extends Pack {

    /**
     * {@code FACTORY} lets the pack registry construct resource packs for
     * manifests whose selected module declares the resources category.
     */
    public static final Pack.Factory FACTORY = ResourcePack::new;

    /**
     * Creates a resource pack from an open loader and parsed manifest.
     * The loader supplies the encryption key, and the base constructor reads
     * archive bytes while retaining the loader for later pack cleanup.
     */
    private ResourcePack(PackLoader loader, PackManifest manifest) {
        super(loader, manifest, loader.getContentKey());
    }

    /**
     * Returns the resources module category represented by client-resource packs.
     */
    @Override
    public Type getType() {
        return Type.RESOURCES;
    }

    /**
     * Returns the fixed diagnostic label for this resource pack implementation.
     */
    @Override
    public String toString() {
        return "ResourcePack{}";
    }
}
