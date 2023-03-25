package cn.allay.block.component;

import cn.allay.math.aabb.AxisAlignedBBRO;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
public interface BlockDataComponent {
    boolean fallable();
    float friction();
    float hardness();
    int resistance();
    boolean solid();
    boolean pushesOutItems();
    int translucency();
    int burnChance();
    int burnAbility();
    int lightFilterFactor();
    boolean experimental();
    boolean flammable();
    boolean replaceable();
    int emitLightFactor();
    boolean diggable();
    boolean powerSource();
    boolean canBreakFallingBlock();
    boolean canBlockWater();
    boolean canBeSilkTouched();
    boolean blockSolid();
    boolean canBeMovingBlock();
    boolean comparatorSignal();
    boolean pushUpFalling();
    boolean waterlogFlowing();
    boolean waterlogSolid();
    int color();
    AxisAlignedBBRO axisAlignedBB();
    //TODO: ToolTypes
}
