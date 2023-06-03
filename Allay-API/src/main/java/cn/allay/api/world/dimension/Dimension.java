package cn.allay.api.world.dimension;

import org.jetbrains.annotations.Range;

/**
 * Author: Cool_Loong | daoge_cmd <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
public record Dimension(int dimensionId, int minHeight, int maxHeight, int chunkSectionSize) {

    public static final Dimension OVERWORLD = new Dimension(0, -64, 319, 24);
    public static final Dimension NETHER = new Dimension(1, 0, 127, 16);
    public static final Dimension THE_END = new Dimension(2, 0, 255, 16);

    public static Dimension of(@Range(from = 0, to = 2) int dimensionId) {
        return switch (dimensionId) {
            case 0 -> OVERWORLD;
            case 1 -> NETHER;
            case 2 -> THE_END;
            default -> null;
        };
    }
}
