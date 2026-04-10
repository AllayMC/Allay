package org.allaymc.server.world.dimension;

import lombok.Getter;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * Built-in dimension identifiers used before the dimension registry is populated.
 *
 * @author daoge_cmd
 */
@Getter
public enum DimensionId {
    OVERWORLD("minecraft:overworld"),
    NETHER("minecraft:nether"),
    THE_END("minecraft:the_end");

    private final Identifier identifier;

    DimensionId(String identifier) {
        this.identifier = new Identifier(identifier);
    }
}
