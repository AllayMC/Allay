package cn.allay.api.world.dimension;

/**
 * Author: Cool_Loong <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
public record DimensionData(int dimensionId, int minHeight, int maxHeight, int chunkSectionSize) {
    public static final int DIMENSION_OVERWORLD = 0;
    public static final int DIMENSION_NETHER = 1;
    public static final int DIMENSION_THE_END = 2;
}
