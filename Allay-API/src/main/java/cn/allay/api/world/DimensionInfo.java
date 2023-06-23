package cn.allay.api.world;

import org.jetbrains.annotations.Range;

/**
 * @author Cool_Loong | daoge_cmd <br>
 * @date 5/31/2023 <br>
 * Allay Project
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
}
