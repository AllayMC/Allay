package org.allaymc.server.world.dimension;

import lombok.Getter;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.dimension.DimensionType;

import java.util.Locale;

/**
 * {@code DimensionId} identifies built-in dimensions before runtime registry
 * initialization and retains their vanilla height and generator defaults.
 * Matching uses namespaced identity rather than numeric ID, so configured
 * bounds can differ while a dimension still matches the corresponding built-in
 * category.
 *
 * <p>
 * Configuration parsing also recognizes convenient built-in spellings.
 * Unqualified names use case-insensitive matching, while qualified names first
 * retain the identifier parser's spelling and then use exact aliases. Both
 * {@code "the-end"} and {@code "the_end"} forms can resolve to the canonical
 * End identifier under those rules. Other names preserve their parsed custom
 * identity rather than being replaced by a built-in fallback, and invalid
 * characters still fail identifier validation.
 *
 * @author daoge_cmd
 */
@Getter
public enum DimensionId {
    /**
     * Built-in Overworld identity and defaults.
     */
    OVERWORLD(0, "minecraft:overworld", -64, 319, VanillaGeneratorType.INFINITE),
    /**
     * Built-in Nether identity and defaults.
     */
    NETHER(1, "minecraft:nether", 0, 127, VanillaGeneratorType.NETHER),
    /**
     * Built-in End identity and defaults.
     */
    THE_END(2, "minecraft:the_end", 0, 255, VanillaGeneratorType.THE_END);

    /**
     * Vanilla numeric dimension identifier.
     */
    private final int id;
    /**
     * Canonical namespaced dimension identifier.
     */
    private final Identifier identifier;
    /**
     * Inclusive vanilla minimum block height.
     */
    private final int defaultMinHeight;
    /**
     * Inclusive vanilla maximum block height.
     */
    private final int defaultMaxHeight;
    /**
     * Vanilla generator compatibility category.
     */
    private final VanillaGeneratorType vanillaGeneratorType;

    /**
     * Creates a built-in identity with vanilla numeric, height, and generator
     * defaults. The identifier text is parsed immediately; stored bounds
     * describe vanilla defaults rather than later configured runtime overrides.
     */
    DimensionId(int id, String identifier, int defaultMinHeight, int defaultMaxHeight, VanillaGeneratorType vanillaGeneratorType) {
        this.id = id;
        this.identifier = new Identifier(identifier);
        this.defaultMinHeight = defaultMinHeight;
        this.defaultMaxHeight = defaultMaxHeight;
        this.vanillaGeneratorType = vanillaGeneratorType;
    }

    /**
     * Returns whether a non-{@code null} dimension type has this built-in namespaced
     * identifier. Numeric IDs and configured heights do not participate, and a
     * {@code null} type simply returns {@code false}.
     */
    public boolean matches(DimensionType dimensionType) {
        return dimensionType != null && matches(dimensionType.getIdentifier());
    }

    /**
     * Returns whether the supplied identifier equals this built-in canonical
     * identifier. The comparison is case-sensitive and includes the namespace;
     * a {@code null} identifier does not match the stored non-{@code null} value.
     */
    public boolean matches(Identifier identifier) {
        return this.identifier.equals(identifier);
    }

    /**
     * Returns whether a type has this built-in identifier and both vanilla
     * height endpoints. A {@code null} or differently identified type returns {@code false}
     * before heights are inspected; numeric ID is not compared.
     */
    public boolean isDefaultBounds(DimensionType dimensionType) {
        return matches(dimensionType) &&
               dimensionType.getMinHeight() == defaultMinHeight &&
               dimensionType.getMaxHeight() == defaultMaxHeight;
    }

    /**
     * Returns the built-in identity matching a dimension type's namespaced
     * identifier, or {@code null} when unmatched. A {@code null} type also
     * returns {@code null}, and numeric IDs do not influence resolution.
     */
    public static DimensionId fromDimensionType(DimensionType dimensionType) {
        return dimensionType == null ? null : fromIdentifier(dimensionType.getIdentifier());
    }

    /**
     * Returns the built-in identity matching the supplied complete identifier,
     * or {@code null} when unmatched. A {@code null} identifier returns {@code
     * null}; this lookup performs no case folding or configuration-alias
     * normalization.
     */
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

    /**
     * Normalizes a trimmed configuration spelling into a namespaced dimension
     * identifier. Unqualified built-in names are matched case-insensitively,
     * with both {@code "the-end"} and {@code "the_end"} selecting the canonical End identity.
     * Qualified identifiers are parsed before exact matching, so only the
     * lowercase {@code "minecraft"} spellings receive those aliases. Other unqualified
     * names retain their original case under the default namespace, and
     * malformed identifiers propagate validation failure rather than becoming a
     * fallback dimension.
     */
    public static Identifier normalizeConfigIdentifier(String rawIdentifier) {
        var normalized = rawIdentifier.trim();
        // Qualified names retain identifier case and use exact aliases;
        // unqualified built-ins use case-insensitive matching, preserving the
        // separate custom-name behavior of each input form.
        if (normalized.contains(Identifier.NAMESPACE_SEPARATOR)) {
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
