package cn.allay.block.component;

import cn.allay.identifier.Identifier;
import cn.allay.math.aabb.AxisAlignedBBRO;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
@Builder
@Getter
@Accessors(fluent = true)
public class BlockDataComponentImpl implements BlockDataComponent, BlockComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_data");

    @Builder.Default
    protected AxisAlignedBBRO aabb = AxisAlignedBBRO.of(0, 0, 0, 1, 1, 1);

    @Builder.Default
    protected boolean blocksPrecipitation = true;

    @Builder.Default
    protected boolean canBeMovingBlock = true;

    @Builder.Default
    protected boolean breaksFallingBlocks = false;

    @Builder.Default
    protected int burnAbility = 0;

    @Builder.Default
    protected int burnChance = 0;

    @Builder.Default
    protected boolean canBeBrokenFromFalling = false;

    @Builder.Default
    protected boolean canContainLiquid = false;

    @Builder.Default
    protected int color = 0;

    @Builder.Default
    protected float explosionResistance = 15;

    @Builder.Default
    protected float friction = 0.6f;

    @Builder.Default
    protected float hardness = 1;

    @Builder.Default
    protected boolean hasBlockEntity = false;

    @Builder.Default
    protected boolean hasComparatorSignal = false;

    @Builder.Default
    protected boolean isAlwaysDestroyable = true;

    @Builder.Default
    protected boolean isContainerBlock = false;

    @Builder.Default
    protected boolean isLiquid = false;

    @Builder.Default
    protected boolean isMotionBlockingBlock = false;

    @Builder.Default
    protected boolean isPowerSource = false;

    @Builder.Default
    protected boolean isSolid = false;

    @Builder.Default
    protected boolean isSolidBlocking = false;

    @Builder.Default
    protected boolean isUnbreakable = false;

    @Builder.Default
    protected boolean isWaterBlocking = true;

    @Builder.Default
    protected boolean flammable = true;

    @Builder.Default
    protected int light = 0;

    @Builder.Default
    protected int lightEmission = 0;

    @Builder.Default
    protected boolean pushesUpFallingBlocks = false;

    @Builder.Default
    protected boolean superHot = false;

    @Builder.Default
    protected float thickness = 0;

    @Builder.Default
    protected float translucency = 0;

    @Builder.Default
    protected boolean waterSpreadCausesSpawn = false;

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
