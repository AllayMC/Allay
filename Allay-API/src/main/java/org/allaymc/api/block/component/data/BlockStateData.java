package org.allaymc.api.block.component.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;

import java.awt.*;

/**
 * Allay Project 2023/5/1
 *
 * @author daoge_cmd | CoolLoong
 */
@Getter
@ToString
@Accessors(fluent = true)
@Builder(toBuilder = true)
@EqualsAndHashCode
public class  BlockStateData {

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

    @Builder.Default
    protected int burnOdds = 0;
    @Builder.Default
    protected boolean canContainLiquid = false;
    @Builder.Default
    protected VoxelShape collisionShape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    @Builder.Default
    protected float fallDamageReductionFactor = 0.0f;
    @Builder.Default
    protected float hardness = 1;
    @Builder.Default
    protected float explosionResistance = 15;
    @Builder.Default
    protected int flameOdds = 0;
    @Builder.Default
    protected float friction = DEFAULT_FRICTION;
    @Builder.Default
    protected int light = 15;
    @Builder.Default
    protected int lightEmission = 0;
    @Builder.Default
    protected Color mapColor = Color.BLACK;
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
}
