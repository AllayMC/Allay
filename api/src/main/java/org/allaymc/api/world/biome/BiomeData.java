package org.allaymc.api.world.biome;

import com.google.gson.annotations.SerializedName;
import org.allaymc.api.annotation.MinecraftVersionSensitive;

import java.awt.*;
import java.util.List;

/**
 * BiomeData represents the data of a biome.
 */
@MinecraftVersionSensitive
public record BiomeData(
        float ashDensity, float blueSporeDensity, float depth,
        float downfall, @SerializedName("mapWaterColour") Color mapWaterColor,
        boolean rain, float redSporeDensity, float scale, List<String> tags,
        float temperature, float whiteAshDensity
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
