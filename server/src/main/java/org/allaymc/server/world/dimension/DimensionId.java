package org.allaymc.server.world.dimension;

import lombok.Getter;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.dimension.DimensionType;

import java.util.Locale;

/**
 * Built-in dimension identifiers used before the dimension registry is populated.
 *
 * @author daoge_cmd
 */
@Getter
public enum DimensionId {
    OVERWORLD(0, "minecraft:overworld", -64, 319, 1),
    NETHER(1, "minecraft:nether", 0, 127, 3),
    THE_END(2, "minecraft:the_end", 0, 255, 4);

    private final int id;
    private final Identifier identifier;
    private final int defaultMinHeight;
    private final int defaultMaxHeight;
    private final int bedrockGeneratorType;

    DimensionId(int id, String identifier, int defaultMinHeight, int defaultMaxHeight, int bedrockGeneratorType) {
        this.id = id;
        this.identifier = new Identifier(identifier);
        this.defaultMinHeight = defaultMinHeight;
        this.defaultMaxHeight = defaultMaxHeight;
        this.bedrockGeneratorType = bedrockGeneratorType;
    }

    public boolean matches(DimensionType dimensionType) {
        return dimensionType != null && matches(dimensionType.getIdentifier());
    }

    public boolean matches(Identifier identifier) {
        return this.identifier.equals(identifier);
    }

    public boolean hasDefaultBounds(DimensionType dimensionType) {
        return matches(dimensionType) &&
               dimensionType.getMinHeight() == defaultMinHeight &&
               dimensionType.getMaxHeight() == defaultMaxHeight;
    }

    public static DimensionId fromDimensionType(DimensionType dimensionType) {
        return dimensionType == null ? null : fromIdentifier(dimensionType.getIdentifier());
    }

    public static DimensionId fromIdentifier(Identifier identifier) {
        if (identifier == null) {
            return null;
        }

        for (var dimensionId : values()) {
            if (dimensionId.matches(identifier)) {
                return dimensionId;
            }
        }
        return null;
    }

    public static Identifier normalizeConfigIdentifier(String rawIdentifier) {
        var normalized = rawIdentifier.trim();
        if (normalized.indexOf(Identifier.NAMESPACE_SEPARATOR) >= 0) {
            var identifier = new Identifier(normalized);
            return switch (identifier.toString()) {
                case "minecraft:overworld" -> OVERWORLD.getIdentifier();
                case "minecraft:nether" -> NETHER.getIdentifier();
                case "minecraft:the-end", "minecraft:the_end" -> THE_END.getIdentifier();
                default -> identifier;
            };
        }

        return switch (normalized.toLowerCase(Locale.ROOT)) {
            case "overworld" -> OVERWORLD.getIdentifier();
            case "nether" -> NETHER.getIdentifier();
            case "the-end", "the_end" -> THE_END.getIdentifier();
            default -> new Identifier(Identifier.DEFAULT_NAMESPACE, normalized);
        };
    }
}
