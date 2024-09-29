package org.allaymc.api.block.component.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.joml.Vector3fc;
import org.joml.Vector3ic;
import org.joml.primitives.AABBf;

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
                return VoxelShape.builder().solid(
                        new AABBf(
                                array.get(0).getAsFloat(), array.get(1).getAsFloat(),
                                array.get(2).getAsFloat(), array.get(3).getAsFloat(),
                                array.get(4).getAsFloat(), array.get(5).getAsFloat()
                        )
                ).build();
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
            }).create();
    /**
     * The probability that the block will be burned while on fire.
     */
    @Builder.Default
    protected int burnOdds = 0;
    /**
     * Whether the block state can contain liquid.
     */
    @Builder.Default
    protected boolean canContainLiquid = false;
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
     * The chance that this block will be ignited when there is a fire nearby
     */
    @Builder.Default
    protected int flameOdds = 0;
    /**
     * The friction of the block state.
     */
    @Builder.Default
    protected float friction = DEFAULT_FRICTION;
    /**
     * The light level of the block state.
     */
    @Builder.Default
    protected int light = 15;
    /**
     * The light emission of the block state.
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

    public static BlockStateData fromJson(String json) {
        return SERIALIZER.fromJson(json, BlockStateData.class);
    }

    public boolean hasCollision() {
        return !collisionShape.getSolids().isEmpty();
    }

    public VoxelShape computeOffsetCollisionShape(float x, float y, float z) {
        return collisionShape.translate(x, y, z);
    }

    public VoxelShape computeOffsetCollisionShape(Vector3fc vector) {
        return computeOffsetCollisionShape(vector.x(), vector.y(), vector.z());
    }

    public VoxelShape computeOffsetCollisionShape(Vector3ic vector) {
        return computeOffsetCollisionShape(vector.x(), vector.y(), vector.z());
    }
}
