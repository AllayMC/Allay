package org.allaymc.api.block.component.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.joml.Vector3dc;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

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

    public static BlockStateData DEFAULT = BlockStateData.builder().build();

    protected static Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(VoxelShape.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var array = json.getAsJsonArray();
                if (array.isEmpty() || array.get(0).isJsonArray()) {
                    // collisionShape field is a list of AABBs, and can be an empty array
                    var builder = VoxelShape.builder();
                    array.forEach(e -> {
                        var a = e.getAsJsonArray();
                        var minX = a.get(0).getAsDouble();
                        var minY = a.get(1).getAsDouble();
                        var minZ = a.get(2).getAsDouble();
                        var maxX = a.get(3).getAsDouble();
                        var maxY = a.get(4).getAsDouble();
                        var maxZ = a.get(5).getAsDouble();

                        builder.solid(minX, minY, minZ, maxX, maxY, maxZ);
                    });
                    return builder.build();
                } else {
                    var minX = array.get(0).getAsDouble();
                    var minY = array.get(1).getAsDouble();
                    var minZ = array.get(2).getAsDouble();
                    var maxX = array.get(3).getAsDouble();
                    var maxY = array.get(4).getAsDouble();
                    var maxZ = array.get(5).getAsDouble();

                    if (minX == 0 && minY == 0 && minZ == 0 && maxX == 0 && maxY == 0 && maxZ == 0) {
                        return VoxelShape.EMPTY;
                    }

                    return VoxelShape.builder().solid(new AABBd(minX, minY, minZ, maxX, maxY, maxZ)).build();
                }
            })
            .registerTypeAdapter(Color.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                // Example: #4c4c4cff
                var str = json.getAsString();
                var color = Color.decode(str.substring(0, 7));
                return new Color(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        Integer.parseInt(str.substring(7), 16));
            })
            .registerTypeAdapter(LiquidReactionOnTouch.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> LiquidReactionOnTouch.valueOf(json.getAsString()))
            .create();
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
     * Collision shape is used to manage the collision with entity.
     */
    @Builder.Default
    protected VoxelShape collisionShape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    /**
     * The shape of the block state.
     */
    @Builder.Default
    protected VoxelShape shape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    /**
     * The hardness of the block state.
     * <p>
     * Hardness will affect the time it takes to break the block.
     * If you set it to -1 then the block will be unbreakable.
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
     * The map color of the block state.
     */
    @Builder.Default
    protected Color mapColor = Color.BLACK;
    /**
     * The thickness of the block state.
     */
    @Builder.Default
    protected float thickness = 0;
    /**
     * Whether the block requires correct tool for drops.
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
     * The translucency of the block state.
     */
    @Builder.Default
    protected float translucency = 0.0f;

    public static BlockStateData fromJson(String json) {
        return SERIALIZER.fromJson(json, BlockStateData.class);
    }

    public static BlockStateData fromJson(JsonObject json) {
        return SERIALIZER.fromJson(json, BlockStateData.class);
    }

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
        return translucency() != 1.0f;
    }

    /**
     * Check if the block state can contain liquid, no matter it is liquid source or not.
     *
     * @return {@code true} if the block state can contain liquid, otherwise {@code false}.
     */
    public boolean canContainLiquid() {
        return canContainLiquidSource || liquidReactionOnTouch.canLiquidFlowInto();
    }
}
