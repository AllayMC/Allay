package cn.allay.block.component.attribute;

import cn.allay.block.component.BlockComponentImpl;
import cn.allay.component.annotation.Inject;
import cn.allay.identifier.Identifier;
import cn.allay.math.aabb.AxisAlignedBBRO;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
@Builder
@Accessors(fluent = true)
public class BlockAttributeComponentImpl implements BlockAttributeComponent, BlockComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_attribute_component");

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

    @Override
    @Inject
    public AxisAlignedBBRO aabb() {
        return aabb;
    }

    @Override
    @Inject
    public boolean blocksPrecipitation() {
        return blocksPrecipitation;
    }

    @Override
    @Inject
    public boolean canBeMovingBlock() {
        return canBeMovingBlock;
    }

    @Override
    @Inject
    public boolean breaksFallingBlocks() {
        return breaksFallingBlocks;
    }

    @Override
    @Inject
    public int burnAbility() {
        return burnAbility;
    }

    @Override
    @Inject
    public int burnChance() {
        return burnChance;
    }

    @Override
    @Inject
    public boolean canBeBrokenFromFalling() {
        return canBeBrokenFromFalling;
    }

    @Override
    @Inject
    public boolean canContainLiquid() {
        return canContainLiquid;
    }

    @Override
    @Inject
    public int color() {
        return color;
    }

    @Override
    @Inject
    public float explosionResistance() {
        return explosionResistance;
    }

    @Override
    @Inject
    public float friction() {
        return friction;
    }

    @Override
    @Inject
    public float hardness() {
        return hardness;
    }

    @Override
    @Inject
    public boolean hasBlockEntity() {
        return hasBlockEntity;
    }

    @Override
    @Inject
    public boolean hasComparatorSignal() {
        return hasComparatorSignal;
    }

    @Override
    @Inject
    public boolean isAlwaysDestroyable() {
        return isAlwaysDestroyable;
    }

    @Override
    @Inject
    public boolean isContainerBlock() {
        return isContainerBlock;
    }

    @Override
    @Inject
    public boolean isLiquid() {
        return isLiquid;
    }

    @Override
    @Inject
    public boolean isMotionBlockingBlock() {
        return isMotionBlockingBlock;
    }

    @Override
    @Inject
    public boolean isPowerSource() {
        return isPowerSource;
    }

    @Override
    @Inject
    public boolean isSolid() {
        return isSolid;
    }

    @Override
    @Inject
    public boolean isSolidBlocking() {
        return isSolidBlocking;
    }

    @Override
    @Inject
    public boolean isUnbreakable() {
        return isUnbreakable;
    }

    @Override
    @Inject
    public boolean isWaterBlocking() {
        return isWaterBlocking;
    }

    @Override
    @Inject
    public boolean flammable() {
        return flammable;
    }

    @Override
    @Inject
    public int light() {
        return light;
    }

    @Override
    @Inject
    public int lightEmission() {
        return lightEmission;
    }

    @Override
    @Inject
    public boolean pushesUpFallingBlocks() {
        return pushesUpFallingBlocks;
    }

    @Override
    @Inject
    public boolean superHot() {
        return superHot;
    }

    @Override
    @Inject
    public float thickness() {
        return thickness;
    }

    @Override
    @Inject
    public float translucency() {
        return translucency;
    }

    @Override
    @Inject
    public boolean waterSpreadCausesSpawn() {
        return waterSpreadCausesSpawn;
    }
}
