package cn.allay.api.world.chunk;

import cn.allay.api.world.DimensionInfo;

/**
 * Allay Project 2023/7/4
 *
 * @author Cool_Loong
 */
public interface UnsafeChunk extends HeightOperate, BlockOperate, SkyLightOperate, BlockLightOperate {
    DimensionInfo getDimensionInfo();
}
