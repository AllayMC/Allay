package cn.allay.api.world.chunk;

import cn.allay.api.world.heightmap.HeightMapType;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/7/4
 *
 * @author Cool_Loong
 */
public interface HeightOperate {
    void setHeight(HeightMapType type, @Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height);

    int getHeight(HeightMapType type, @Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);
}
