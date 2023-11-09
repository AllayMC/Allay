package org.allaymc.api.world;

import org.allaymc.api.identifier.Identifier;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/5/31
 *
 * @author Cool_Loong | daoge_cmd
 */
public record DimensionInfo(int dimensionId,
                            @Range(from = -512, to = 512) int minHeight,
                            @Range(from = -512, to = 512) int maxHeight,
                            int chunkSectionSize) {

    public static final DimensionInfo OVERWORLD = new DimensionInfo(0, -64, 319, 24);
    public static final DimensionInfo NETHER = new DimensionInfo(1, 0, 127, 16);
    public static final DimensionInfo THE_END = new DimensionInfo(2, 0, 255, 16);

    public DimensionInfo(int dimensionId,
                         @Range(from = -512, to = 512) int minHeight,
                         @Range(from = -512, to = 512) int maxHeight) {
        this(dimensionId, minHeight, maxHeight, (maxHeight - minHeight + 1) / 16);
    }

    public static DimensionInfo of(@Range(from = 0, to = 2) int dimensionId) {
        return switch (dimensionId) {
            case 0 -> OVERWORLD;
            case 1 -> NETHER;
            case 2 -> THE_END;
            default -> null;
        };
    }

    public int minSectionY() {
        return this.minHeight >> 4;
    }

    public int maxSectionY() {
        return this.maxHeight >> 4;
    }

    public static DimensionInfo fromName(Identifier name) {
        return switch (name.toString()) {
            case "minecraft:overworld" -> OVERWORLD;
            case "minecraft:nether" -> NETHER;
            case "minecraft:the_end" -> THE_END;
            default -> null;
        };
    }
}
