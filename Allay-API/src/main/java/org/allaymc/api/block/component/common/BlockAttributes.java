package org.allaymc.api.block.component.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.utils.AllayStringUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;

import java.awt.*;

/**
 * BlockAttributes store some of the metadata of the block
 * <p>
 * Allay Project 2023/5/1
 *
 * @author daoge_cmd | CoolLoong
 */
@Getter
@Accessors(fluent = true)
@Builder(toBuilder = true)
@EqualsAndHashCode
public class BlockAttributes {

    /**
     * See: <a href="https://www.mcpk.wiki/wiki/Slipperiness">Slipperiness</a>
     */
    public static final float DEFAULT_FRICTION = 0.6f;
    public static BlockAttributes DEFAULT = BlockAttributes.builder().build();
    protected static Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(VoxelShape.class, (JsonDeserializer<Object>) (json, typeOfT, context) ->
                    VoxelShape.builder().solid(parseAABBStr(json.getAsString())).build())
            .registerTypeAdapter(Color.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var r = json.getAsJsonObject().get("r").getAsInt();
                var g = json.getAsJsonObject().get("g").getAsInt();
                var b = json.getAsJsonObject().get("b").getAsInt();
                var a = json.getAsJsonObject().get("a").getAsInt();
                return new Color(r, g, b, a);
            })
            .create();
    /**
     * block collision box
     */
    @Builder.Default
    @SerializedName("aabbCollision")
    protected VoxelShape voxelShape = VoxelShape.builder().solid(0, 0, 0, 1, 1, 1).build();
    @Builder.Default
    protected Color color = Color.BLACK;
    @Builder.Default
    protected String descriptionId = "";
    @Builder.Default
    protected int burnAbility = 0;
    @Builder.Default
    protected int burnChance = 0;
    @Builder.Default
    protected int lightDampening = 0;
    @Builder.Default
    protected int lightEmission = 0;
    @Builder.Default
    protected float explosionResistance = 15;
    @Builder.Default
    protected float friction = DEFAULT_FRICTION;
    @Builder.Default
    protected float hardness = 1;
    @Builder.Default
    protected float thickness = 0;
    @Builder.Default
    protected boolean canContainLiquid = false;
    @Builder.Default
    protected boolean hasCollision = true;
    /**
     * Whether the block blocking movement
     */
    @Builder.Default
    protected boolean isMotionBlockingBlock = false;
    @Builder.Default
    protected boolean isPowerSource = false;
    @Builder.Default
    protected boolean isUnbreakable = false;
    @Builder.Default
    protected boolean isWaterBlocking = true;
    @Builder.Default
    protected boolean isLavaFlammable = true;
    /**
     * representing a piston block type
     */
    @Builder.Default
    protected boolean pushesUpFallingBlocks = false;

    protected static AABBf parseAABBStr(String str) {
        var numbers = AllayStringUtils.fastSplit(str, ",").stream().map(Float::valueOf).toList();
        return new AABBf(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4), numbers.get(5));
    }

    public static BlockAttributes fromJson(String json) {
        return SERIALIZER.fromJson(json, BlockAttributes.class);
    }

    // TODO: test
    public static BlockAttributes fromNBT(NbtMap nbt) {
        var colorObj = nbt.getCompound("color");
        var color = new Color(
                colorObj.getInt("r"),
                colorObj.getInt("g"),
                colorObj.getInt("b"),
                colorObj.getInt("a")
        );
        return BlockAttributes
                .builder()
                .voxelShape(VoxelShape.builder().solid(parseAABBStr(nbt.getString("aabbCollision"))).build())
                .hasCollision(nbt.getBoolean("hasCollision"))
                .burnAbility(nbt.getInt("burnAbility"))
                .burnChance(nbt.getInt("burnChance"))
                .canContainLiquid(nbt.getBoolean("canContainLiquid"))
                .color(color)
                .descriptionId(nbt.getString("descriptionId"))
                .explosionResistance(nbt.getFloat("explosionResistance"))
                .friction(nbt.getFloat("friction"))
                .hardness(nbt.getFloat("hardness"))
                .isMotionBlockingBlock(nbt.getBoolean("isMotionBlockingBlock"))
                .isPowerSource(nbt.getBoolean("isPowerSource"))
                .isUnbreakable(nbt.getBoolean("isUnbreakable"))
                .isWaterBlocking(nbt.getBoolean("isWaterBlocking"))
                .lightEmission(nbt.getInt("lightEmission"))
                .lightDampening(nbt.getInt("lightDampening"))
                .isLavaFlammable(nbt.getBoolean("isLavaFlammable"))
                .pushesUpFallingBlocks(nbt.getBoolean("pushesUpFallingBlocks"))
                .thickness(nbt.getFloat("thickness"))
                .build();
    }

    public VoxelShape computeOffsetVoxelShape(float x, float y, float z) {
        return voxelShape.translate(x, y, z);
    }

    public VoxelShape computeOffsetVoxelShape(Vector3fc vector) {
        return computeOffsetVoxelShape(vector.x(), vector.y(), vector.z());
    }
}
