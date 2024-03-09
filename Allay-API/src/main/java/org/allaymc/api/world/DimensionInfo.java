package org.allaymc.api.world;

import com.google.common.base.Preconditions;
import org.allaymc.api.client.data.Identifier;

/**
 * Allay Project 2023/5/31
 *
 * @author Cool_Loong | daoge_cmd
 */
public record DimensionInfo(
        int dimensionId,
        int minHeight,
        int maxHeight,
        int chunkSectionSize
) {

    public static final DimensionInfo OVERWORLD = new DimensionInfo(0, -64, 319, 24);
    public static final DimensionInfo NETHER = new DimensionInfo(1, 0, 127, 16);
    public static final DimensionInfo THE_END = new DimensionInfo(2, 0, 255, 16);

    public DimensionInfo(int dimensionId, int minHeight, int maxHeight) {
        this(dimensionId, minHeight, maxHeight, (maxHeight - minHeight + 1) / 16);
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

    public static DimensionInfo fromName(Identifier name) {
        return switch (name.toString()) {
            case "minecraft:overworld" -> OVERWORLD;
            case "minecraft:nether" -> NETHER;
            case "minecraft:the_end" -> THE_END;
            default -> null;
        };
    }

    public int minSectionY() {
        return this.minHeight >> 4;
    }

    public int maxSectionY() {
        return this.maxHeight >> 4;
    }
}
