package org.allaymc.api.world.biome;

import org.allaymc.api.annotation.MinecraftVersionSensitive;

import java.awt.*;
import java.util.List;

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

    /**
     * Determines whether the biome is considered humid based on its downfall value.
     *
     * @return {@code true} if the downfall value is greater than or equal to 0.85, {@code false} otherwise
     */
    public boolean isHumid() {
        return downfall >= 0.85;
    }
}
