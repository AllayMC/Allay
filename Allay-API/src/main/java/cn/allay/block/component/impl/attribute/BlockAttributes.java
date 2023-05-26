package cn.allay.block.component.impl.attribute;

import cn.allay.math.aabb.AxisAlignedBBRO;
import cn.allay.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/1 <br>
 * Allay Project <br>
 */
@Getter
@Accessors(fluent = true)
@Builder
@EqualsAndHashCode
public class BlockAttributes {
    protected static Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(AxisAlignedBBRO.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var numbers = StringUtils.fastSplit(json.getAsString(), ",").stream().map(Float::valueOf).toList();
                return AxisAlignedBBRO.of(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4), numbers.get(5));
            })
            .registerTypeAdapter(Color.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var r = json.getAsJsonObject().get("r").getAsInt();
                var g = json.getAsJsonObject().get("g").getAsInt();
                var b = json.getAsJsonObject().get("b").getAsInt();
                var a = json.getAsJsonObject().get("a").getAsInt();
                return new Color(r, g, b, a);
            })
            .create();

    public static BlockAttributes DEFAULT = BlockAttributes.builder().build();

    public static BlockAttributes of(String json) {
        return SERIALIZER.fromJson(json, BlockAttributes.class);
    }

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
    protected Color color = Color.BLACK;
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
}
