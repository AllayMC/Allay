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
import org.allaymc.api.message.MayContainTrKey;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.awt.*;

/**
 * {@code BlockStateData} holds the physical, interaction, lighting, and rendering
 * metadata consumed for a block state. The generated builder supplies defaults,
 * while the builder and {@code toBuilder} retain {@code collisionShape} and {@code
 * shape} references. Generated accessors return those same shapes, so callers must
 * preserve their mutable solid sets after publishing metadata. Translation helpers
 * produce world-relative geometry without changing the stored shapes.
 *
 * @author daoge_cmd | CoolLoong
 */
@Getter
@ToString
@Accessors(fluent = true)
@Builder(toBuilder = true)
@EqualsAndHashCode
public class BlockStateData {

    /**
     * {@code DEFAULT_FRICTION} supplies the default slipperiness factor used when
     * the builder does not override block friction.
     */
    public static final float DEFAULT_FRICTION = 0.6f;

    /**
     * {@code DEFAULT} is the shared metadata object built from every declared
     * default when no specialized state data is supplied.
     */
    public static final BlockStateData DEFAULT = BlockStateData.builder().build();

    /**
     * {@code burnOdds} controls the fire burn-away chance. Fire updates compare a
     * random value with this number, and the default {@code 0} prevents that
     * comparison from selecting the block.
     */
    @Builder.Default
    protected int burnOdds = 0;
    /**
     * {@code canContainLiquidSource} permits a source liquid in the secondary
     * layer. Flowing-liquid acceptance remains governed separately by {@code
     * liquidReactionOnTouch}.
     */
    @Builder.Default
    protected boolean canContainLiquidSource = false;
    /**
     * {@code liquidReactionOnTouch} selects removal, coexistence, or blocking when
     * liquid reaches the block. The default {@code BLOCKING} rejects flowing liquid
     * independently of source containment.
     */
    @Builder.Default
    protected LiquidReactionOnTouch liquidReactionOnTouch = LiquidReactionOnTouch.BLOCKING;
    /**
     * {@code collisionShape} holds block-relative collision geometry. Entity
     * collision checks use its translated solids before invoking {@link
     * BlockBehavior#onCollideWithEntity(Block, Entity)}, and generated builders
     * retain the supplied shape reference.
     */
    @Builder.Default
    protected VoxelShape collisionShape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    /**
     * {@code shape} holds the block-relative selection and inside-detection
     * geometry. Entity checks use it for {@link BlockBehavior#onEntityInside(Block,
     * Entity)} and {@link Entity#onInsideBlock(Block)}, independently of physical
     * collision geometry.
     */
    @Builder.Default
    protected VoxelShape shape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    /**
     * {@code hardness} controls block-breaking time. A value of {@code -1} selects
     * the unbreakable result in the base block behavior rather than a negative
     * breaking duration.
     */
    @Builder.Default
    protected float hardness = 1;
    /**
     * Explosion damage resistance factor.
     */
    @Builder.Default
    protected float explosionResistance = 15;
    /**
     * {@code flameOdds} controls ignition and fire-spread selection. Positive
     * values identify flammable neighbors for fire and lava behavior, while the
     * default {@code 0} supplies no ignition chance.
     */
    @Builder.Default
    protected int flameOdds = 0;
    /**
     * Block surface slipperiness factor.
     */
    @Builder.Default
    protected float friction = DEFAULT_FRICTION;
    /**
     * {@code lightDampening} stores light attenuation on the {@code 0} to {@code
     * 15} light scale. The default {@code 15} blocks light, and {@code
     * isTransparent} recognizes only {@code 0}.
     */
    @Builder.Default
    protected int lightDampening = 15;
    /**
     * Emitted light level, {@code 0} to {@code 15}.
     */
    @Builder.Default
    protected int lightEmission = 0;
    /**
     * {@code mapColor} holds the base map-rendering color. {@code tintMethod}
     * selects any later color treatment, so this value alone need not be the final
     * displayed map pixel.
     */
    @Builder.Default
    protected Color mapColor = Color.BLACK;
    /**
     * Map and client tint selection.
     */
    @Builder.Default
    protected TintMethod tintMethod = TintMethod.NONE;
    /**
     * Stored block thickness value.
     */
    @Builder.Default
    protected float thickness = 0;
    /**
     * {@code requiresCorrectToolForDrops} makes the base behavior require a
     * suitable tool for drops when {@code true}. A value of {@code false} allows
     * that check to pass without a correct tool.
     */
    @Builder.Default
    protected boolean requiresCorrectToolForDrops = true;
    /**
     * Solid-block classification flag.
     */
    @Builder.Default
    protected boolean isSolid = true;
    /**
     * Stored map translucency value.
     */
    @Builder.Default
    protected float translucency = 0.0f;
    /**
     * Block display translation key.
     */
    @MayContainTrKey
    @Builder.Default
    protected String translationKey = "";

    /**
     * Returns whether the stored collision shape contains any solid boxes.
     */
    public boolean hasCollision() {
        return !collisionShape.getSolids().isEmpty();
    }

    /**
     * Returns the collision shape translated by the supplied block-coordinate
     * offsets. The stored shape remains unchanged; an empty shape may return the
     * shared empty shape rather than a new object.
     */
    public VoxelShape computeOffsetCollisionShape(double x, double y, double z) {
        return collisionShape.translate(x, y, z);
    }

    /**
     * Returns the collision shape translated by the supplied vector's coordinates.
     * This overload reads the vector without retaining it and preserves the stored
     * shape, including its block-relative geometry.
     */
    public VoxelShape computeOffsetCollisionShape(Vector3dc vector) {
        return computeOffsetCollisionShape(vector.x(), vector.y(), vector.z());
    }

    /**
     * Returns the collision shape translated by the supplied vector's coordinates.
     * This overload reads the vector without retaining it and preserves the stored
     * shape, including its block-relative geometry.
     */
    public VoxelShape computeOffsetCollisionShape(Vector3ic vector) {
        return computeOffsetCollisionShape(vector.x(), vector.y(), vector.z());
    }

    /**
     * Returns the selection and inside-detection shape translated by the supplied
     * block-coordinate offsets. The stored shape remains unchanged; an empty shape
     * may return the shared empty shape rather than a new object.
     */
    public VoxelShape computeOffsetShape(double x, double y, double z) {
        return shape.translate(x, y, z);
    }

    /**
     * Returns the selection and inside-detection shape translated by the supplied
     * vector's coordinates. This overload reads the vector without retaining it and
     * preserves the stored shape, including its block-relative geometry.
     */
    public VoxelShape computeOffsetShape(Vector3dc vector) {
        return computeOffsetShape(vector.x(), vector.y(), vector.z());
    }

    /**
     * Returns the selection and inside-detection shape translated by the supplied
     * vector's coordinates. This overload reads the vector without retaining it and
     * preserves the stored shape, including its block-relative geometry.
     */
    public VoxelShape computeOffsetShape(Vector3ic vector) {
        return computeOffsetShape(vector.x(), vector.y(), vector.z());
    }

    /**
     * Returns whether light attenuation is exactly zero for this state data.
     */
    public boolean isTransparent() {
        return lightDampening() == 0;
    }

    /**
     * Returns whether either source containment or unchanged flowing-liquid
     * coexistence is allowed. This combined check does not distinguish source
     * placement from flow, so callers requiring source containment must query
     * {@code canContainLiquidSource} separately.
     */
    public boolean canContainLiquid() {
        return canContainLiquidSource || liquidReactionOnTouch.canLiquidFlowInto();
    }
}
