package cn.allay.block.component.impl.attribute;

import cn.allay.block.component.BlockComponentImpl;
import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import cn.allay.math.aabb.AxisAlignedBBRO;
import lombok.Builder;
import lombok.experimental.Accessors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
@Builder
@Accessors(fluent = true)
public class BlockAttributeComponentImpl implements BlockAttributeComponent, BlockComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_attribute_component");
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

    public static BlockAttributeComponentImpl of(String json) {
        return SERIALIZER.fromJson(json, BlockAttributeComponentImpl.class);
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public AxisAlignedBBRO aabb() {
        return aabb;
    }

    @Override
    @Impl
    public boolean blocksPrecipitation() {
        return blocksPrecipitation;
    }

    @Override
    @Impl
    public boolean canBeMovingBlock() {
        return canBeMovingBlock;
    }

    @Override
    @Impl
    public boolean breaksFallingBlocks() {
        return breaksFallingBlocks;
    }

    @Override
    @Impl
    public int burnAbility() {
        return burnAbility;
    }

    @Override
    @Impl
    public int burnChance() {
        return burnChance;
    }

    @Override
    @Impl
    public boolean canBeBrokenFromFalling() {
        return canBeBrokenFromFalling;
    }

    @Override
    @Impl
    public boolean canContainLiquid() {
        return canContainLiquid;
    }

    @Override
    @Impl
    public int color() {
        return color;
    }

    @Override
    @Impl
    public float explosionResistance() {
        return explosionResistance;
    }

    @Override
    @Impl
    public float friction() {
        return friction;
    }

    @Override
    @Impl
    public float hardness() {
        return hardness;
    }

    @Override
    @Impl
    public boolean hasBlockEntity() {
        return hasBlockEntity;
    }

    @Override
    @Impl
    public boolean hasComparatorSignal() {
        return hasComparatorSignal;
    }

    @Override
    @Impl
    public boolean isAlwaysDestroyable() {
        return isAlwaysDestroyable;
    }

    @Override
    @Impl
    public boolean isContainerBlock() {
        return isContainerBlock;
    }

    @Override
    @Impl
    public boolean isLiquid() {
        return isLiquid;
    }

    @Override
    @Impl
    public boolean isMotionBlockingBlock() {
        return isMotionBlockingBlock;
    }

    @Override
    @Impl
    public boolean isPowerSource() {
        return isPowerSource;
    }

    @Override
    @Impl
    public boolean isSolid() {
        return isSolid;
    }

    @Override
    @Impl
    public boolean isSolidBlocking() {
        return isSolidBlocking;
    }

    @Override
    @Impl
    public boolean isUnbreakable() {
        return isUnbreakable;
    }

    @Override
    @Impl
    public boolean isWaterBlocking() {
        return isWaterBlocking;
    }

    @Override
    @Impl
    public boolean flammable() {
        return flammable;
    }

    @Override
    @Impl
    public int light() {
        return light;
    }

    @Override
    @Impl
    public int lightEmission() {
        return lightEmission;
    }

    @Override
    @Impl
    public boolean pushesUpFallingBlocks() {
        return pushesUpFallingBlocks;
    }

    @Override
    @Impl
    public boolean superHot() {
        return superHot;
    }

    @Override
    @Impl
    public float thickness() {
        return thickness;
    }

    @Override
    @Impl
    public float translucency() {
        return translucency;
    }

    @Override
    @Impl
    public boolean waterSpreadCausesSpawn() {
        return waterSpreadCausesSpawn;
    }
}
