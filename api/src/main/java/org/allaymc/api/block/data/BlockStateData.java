package org.allaymc.api.block.data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.awt.*;

/**
 * @author daoge_cmd | CoolLoong
 */
@Getter
@ToString
@Accessors(fluent = true)
@Builder(toBuilder = true)
@EqualsAndHashCode
public class BlockStateData {

    /**
     * @see <a href="https://www.mcpk.wiki/wiki/Slipperiness">Slipperiness</a>
     */
    public static final float DEFAULT_FRICTION = 0.6f;

    public static final BlockStateData DEFAULT = BlockStateData.builder().build();

    /**
     * The burnOdds of this block state.
     * <p>
     * The higher the burn odds, the more quickly a block on fire burns away
     */
    @Builder.Default
    protected int burnOdds = 0;
    /**
     * Whether the block state can contain liquid source.
     */
    @Builder.Default
    protected boolean canContainLiquidSource = false;
    /**
     * The reaction of this block state when liquid flow into.
     */
    @Builder.Default
    protected LiquidReactionOnTouch liquidReactionOnTouch = LiquidReactionOnTouch.BLOCKING;
    /**
     * The collision shape of the block state.
     * <p>
     * When an entity is collided with block's collision shape, methods {@link BlockBehavior#onCollideWithEntity(Block, Entity)}
     * and {@link Entity#onInsideBlock(Block)} will be called.
     */
    @Builder.Default
    protected VoxelShape collisionShape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    /**
     * The shape of the block state. When an entity is collided with block's shape, both
     * {@link BlockBehavior#onEntityInside(Block, Entity)} and
     * {@link Entity#onInsideBlock(Block)} will be called.
     * <p>
     * This is useful for some blocks that do not have collision shape, such as lava and
     * fire. These two blocks rely on the above method to ignite entity inside.
     */
    @Builder.Default
    protected VoxelShape shape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    /**
     * The hardness of the block state.
     * <p>
     * Hardness will affect the time it takes to break the block.
     * If you set it to {@code -1} then the block will be unbreakable.
     */
    @Builder.Default
    protected float hardness = 1;
    /**
     * The explosion resistance of the block state.
     */
    @Builder.Default
    protected float explosionResistance = 15;
    /**
     * The flame odds of the block state.
     * <p>
     * The higher the flame odds, the more quickly a block catches fire if the fire is available to spread there.
     */
    @Builder.Default
    protected int flameOdds = 0;
    /**
     * The friction of the block state.
     */
    @Builder.Default
    protected float friction = DEFAULT_FRICTION;
    /**
     * The amount that light will be dampened when it passes through the block, in a range (0-15).
     * Higher value means the light will be dampened more.
     */
    @Builder.Default
    protected int lightDampening = 15;
    /**
     * The amount of light this block will emit in a range (0-15).
     * Higher value means more light will be emitted.
     */
    @Builder.Default
    protected int lightEmission = 0;
    /**
     * The map color of the block state. Please note that this is not the final map color, and
     * the final map color will be calculated dynamically based on the {@link TintMethod} of the
     * block. Some block's mapColor may return `#00000000`, and this is expected behavior since
     * these blocks have special tint method (e.g. minecraft:grass_block).
     */
    @Builder.Default
    protected Color mapColor = Color.BLACK;
    /**
     * The tint method of the block state. Used in map rendering.
     */
    @Builder.Default
    protected TintMethod tintMethod = TintMethod.NONE;
    /**
     * The thickness of the block state.
     */
    @Builder.Default
    protected float thickness = 0;
    /**
     * Whether the block requires the correct tool for drops.
     * If set to {@code false}, breaking this block with
     * wrong tool won't have any drop.
     */
    @Builder.Default
    protected boolean requiresCorrectToolForDrops = true;
    /**
     * Whether this block is a solid block.
     */
    @Builder.Default
    protected boolean isSolid = true;
    /**
     * The translucency of the block state. Please note that this value is only used in map rendering.
     */
    @Builder.Default
    protected float translucency = 0.0f;

    public boolean hasCollision() {
        return !collisionShape.getSolids().isEmpty();
    }

    public VoxelShape computeOffsetCollisionShape(double x, double y, double z) {
        return collisionShape.translate(x, y, z);
    }

    public VoxelShape computeOffsetCollisionShape(Vector3dc vector) {
        return computeOffsetCollisionShape(vector.x(), vector.y(), vector.z());
    }

    public VoxelShape computeOffsetCollisionShape(Vector3ic vector) {
        return computeOffsetCollisionShape(vector.x(), vector.y(), vector.z());
    }

    public VoxelShape computeOffsetShape(double x, double y, double z) {
        return shape.translate(x, y, z);
    }

    public VoxelShape computeOffsetShape(Vector3dc vector) {
        return computeOffsetShape(vector.x(), vector.y(), vector.z());
    }

    public VoxelShape computeOffsetShape(Vector3ic vector) {
        return computeOffsetShape(vector.x(), vector.y(), vector.z());
    }

    public boolean isTransparent() {
        return lightDampening() == 0;
    }

    /**
     * Check if the block state can contain liquid, no matter it is a liquid source or not.
     *
     * @return {@code true} if the block state can contain liquid, otherwise {@code false}.
     */
    public boolean canContainLiquid() {
        return canContainLiquidSource || liquidReactionOnTouch.canLiquidFlowInto();
    }
}
