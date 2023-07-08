package cn.allay.api.world.chunk;

import cn.allay.api.world.biome.BiomeType;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
public interface BiomeOperate {
    void setBiomeType(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    BiomeType getBiomeType(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z);
}
