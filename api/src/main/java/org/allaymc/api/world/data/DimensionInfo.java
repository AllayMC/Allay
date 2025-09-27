package org.allaymc.api.world.data;

import com.google.common.base.Preconditions;

/**
 * DimensionInfo contains the information about a dimension.
 *
 * @author Cool_Loong | daoge_cmd
 */
public record DimensionInfo(int dimensionId, int minHeight, int maxHeight, int chunkSectionCount, boolean hasSkyLight) {

    public static final DimensionInfo OVERWORLD = new DimensionInfo(0, -64, 319, 24, true);
    public static final DimensionInfo NETHER = new DimensionInfo(1, 0, 127, 8, false);
    public static final DimensionInfo THE_END = new DimensionInfo(2, 0, 255, 16, false);

    public DimensionInfo(int dimensionId, int minHeight, int maxHeight, boolean hasSkyLight) {
        this(dimensionId, minHeight, maxHeight, (maxHeight - minHeight + 1) / 16, hasSkyLight);
        Preconditions.checkArgument(minHeight >= -512 && minHeight <= 512);
        Preconditions.checkArgument(maxHeight >= -512 && maxHeight <= 512);
    }

    public static DimensionInfo of(int dimensionId) {
        Preconditions.checkArgument(dimensionId >= 0 && dimensionId <= 2);
        return switch (dimensionId) {
            case 0 -> OVERWORLD;
            case 1 -> NETHER;
            case 2 -> THE_END;
            default -> null;
        };
    }

    public static DimensionInfo fromName(String name) {
        return switch (name) {
            case "overworld" -> OVERWORLD;
            case "nether" -> NETHER;
            case "the_end" -> THE_END;
            default -> null;
        };
    }

    public int minSectionY() {
        return this.minHeight >> 4;
    }

    public int maxSectionY() {
        return this.maxHeight >> 4;
    }

    @Override
    public String toString() {
        return switch (this.dimensionId) {
            case 0 -> "overworld";
            case 1 -> "nether";
            case 2 -> "the_end";
            default -> null;
        };
    }
}
