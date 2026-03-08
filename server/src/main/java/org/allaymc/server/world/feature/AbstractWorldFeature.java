package org.allaymc.server.world.feature;

import lombok.Getter;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeature;

/**
 * Abstract base class for world features.
 * Handles identifier storage and automatic registry registration.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class AbstractWorldFeature implements WorldFeature {

    protected final Identifier identifier;

    public AbstractWorldFeature(Identifier identifier) {
        this.identifier = identifier;
        Registries.WORLD_FEATURES.register(identifier, this);
    }
}
