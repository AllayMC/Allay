package org.allaymc.api.world.biome;

import lombok.Builder;
import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.entity.property.enums.ClimateVariant;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * {@code BiomeData} carries climate, terrain, particle, and rendering properties for a biome
 * type. The record and generated builder retain the supplied tag list; callers
 * sharing mutable lists can therefore change later climate classification
 * without constructing another biome record.
 *
 * <p>
 * Entity climate classification uses tag categories rather than numerical
 * temperature. Any cold tag wins before warm tags are considered, and an
 * unmatched tag list selects the temperate variant. Humidity is a separate
 * downfall threshold, so it does not override that tag precedence. Numeric
 * values are retained without range checks, and consumers such as biome packet
 * encoders interpret the stored properties according to their own
 * representation.
 *
 * @param temperature biome temperature parameter
 * @param downfall biome downfall parameter
 * @param redSporeDensity red-spore particle density
 * @param blueSporeDensity blue-spore particle density
 * @param ashDensity ash particle density
 * @param whiteAshDensity white-ash particle density
 * @param foliageSnow foliage snow parameter
 * @param depth biome terrain-depth parameter
 * @param scale biome terrain-scale parameter
 * @param mapWaterColor map water rendering color
 * @param rain biome rainfall availability flag
 * @param tags {@code tags} retains the supplied biome-tag list. Callers must
 *             keep it stable while shared consumers classify the biome or
 *             encode its properties.
 */
@Builder
@MinecraftVersionSensitive
public record BiomeData(
        float temperature, float downfall,
        float redSporeDensity, float blueSporeDensity, float ashDensity, float whiteAshDensity,
        float foliageSnow, float depth, float scale,
        Color mapWaterColor, boolean rain, List<BiomeTag> tags
) {

    /**
     * {@code COLD_TAGS} identifies cold entity-variant categories, which take
     * precedence whenever a biome also contains warm-category tags.
     */
    private static final Set<BiomeTag> COLD_TAGS = Set.of(
            BiomeTags.TAIGA, BiomeTags.EXTREME_HILLS, BiomeTags.FROZEN, BiomeTags.DEEP, BiomeTags.THE_END
    );

    /**
     * {@code WARM_TAGS} identifies warm entity-variant categories consulted only
     * after no cold-category tag matched the biome's tag list.
     */
    private static final Set<BiomeTag> WARM_TAGS = Set.of(
            BiomeTags.SAVANNA, BiomeTags.JUNGLE, BiomeTags.MESA, BiomeTags.DESERT, BiomeTags.LUKEWARM, BiomeTags.SWAMP, BiomeTags.NETHER
    );

    /**
     * Returns whether the stored downfall meets the biome humidity threshold of
     * {@code 0.85}. Temperature, rainfall availability, and climate-category tags do
     * not participate in this separate humidity classification.
     */
    public boolean isHumid() {
        // Humidity follows downfall alone, independently of temperature and
        // entity climate tags.
        return downfall >= 0.85;
    }

    /**
     * Selects the entity climate variant by examining the retained biome tags.
     * Cold-category tags are scanned first, so one cold match wins even when
     * the list also contains warm tags or lists them earlier.
     * {@code ClimateVariant.WARM} is selected only after all cold checks fail;
     * otherwise the result is {@code ClimateVariant.TEMPERATE}.
     * The tag list must not be {@code null} and must remain stable during these
     * passes, because record construction neither copies nor validates it.
     */
    public ClimateVariant getEntityClimateVariant() {
        // Scan all cold candidates before considering warm ones so
        // mixed-category biomes consistently select cold, regardless of the
        // input tag order.
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

        // Unmatched tag categories use the temperate entity variant; numerical
        // temperature does not supply an additional fallback or override for
        // this selection.
        return ClimateVariant.TEMPERATE;
    }
}
