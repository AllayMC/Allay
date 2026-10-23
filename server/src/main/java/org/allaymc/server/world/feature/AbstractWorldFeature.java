package org.allaymc.server.world.feature;

import lombok.Getter;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeature;

/**
 * {@code AbstractWorldFeature} supplies the registered identifier shared by
 * concrete feature implementations. Its constructor immediately registers each
 * instance in {@code Registries.WORLD_FEATURES}, so construction requires
 * registry initialization and exposes the subclass before its own constructor
 * finishes.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class AbstractWorldFeature implements WorldFeature {

    /**
     * Registered namespaced feature identifier.
     */
    protected final Identifier identifier;

    /**
     * Stores the feature identifier and registers this instance immediately.
     * The registry must already exist, and subclass fields have not yet been
     * initialized when registration publishes the reference.
     */
    public AbstractWorldFeature(Identifier identifier) {
        this.identifier = identifier;
        Registries.WORLD_FEATURES.register(identifier, this);
    }
}
