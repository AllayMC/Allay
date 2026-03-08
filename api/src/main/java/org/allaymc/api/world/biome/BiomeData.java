package org.allaymc.api.world.biome;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.entity.property.enums.ClimateVariant;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * BiomeData contains the data of a {@link BiomeType}.
 */
@MinecraftVersionSensitive
public record BiomeData(
        float temperature, float downfall,
        float redSporeDensity, float blueSporeDensity, float ashDensity, float whiteAshDensity,
        float foliageSnow, float depth, float scale,
        Color mapWaterColor, boolean rain, List<String> tags
) {

    private static final Set<String> COLD_TAGS = Set.of(
            "taiga", "extreme_hills", "frozen", "deep_down", "the_end"
    );

    private static final Set<String> WARM_TAGS = Set.of(
            "savanna", "jungle", "mesa", "desert", "lukewarm", "swamp", "nether"
    );

    /**
     * Determines whether the biome is considered humid based on its downfall value.
     *
     * @return {@code true} if the downfall value is greater than or equal to 0.85, {@code false} otherwise
     */
    public boolean isHumid() {
        return downfall >= 0.85;
    }

    /**
     * Determines the entity's climate variant based on this biome's tags.
     *
     * @return the entity's climate variant for this biome
     */
    public ClimateVariant getEntityClimateVariant() {
        for (var tag : tags) {
            if (COLD_TAGS.contains(tag)) {
                return ClimateVariant.COLD;
            }
        }

        for (var tag : tags) {
            if (WARM_TAGS.contains(tag)) {
                return ClimateVariant.WARM;
            }
        }

        return ClimateVariant.TEMPERATE;
    }
}
