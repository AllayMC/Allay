package cn.allay.block.component;

import cn.allay.math.aabb.AxisAlignedBBRO;
import cn.allay.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
public interface BlockDataComponent {

    Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(AxisAlignedBBRO.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var originStr = json.getAsString();
                var numbers = StringUtils.fastSplit(originStr.substring(24, originStr.length() - 1), ",").stream().map(Float::valueOf).toList();
                return AxisAlignedBBRO.of(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4), numbers.get(5));
            })
            .create();

    static BlockDataComponent of(String json) {
        return SERIALIZER.fromJson(json, BlockDataComponentImpl.class);
    }

    boolean fallable();
    float friction();
    float hardness();
    int resistance();
    boolean solid();
    boolean pushesOutItems();
    int translucency();
    int burnChance();
    int burnAbility();
    int lightFilterFactor();
    boolean experimental();
    boolean flammable();
    boolean replaceable();
    int emitLightFactor();
    boolean diggable();
    boolean powerSource();
    boolean canBreakFallingBlock();
    boolean canBlockWater();
    boolean canBeSilkTouched();
    boolean blockSolid();
    boolean canBeMovingBlock();
    boolean comparatorSignal();
    boolean pushUpFalling();
    boolean waterlogFlowing();
    boolean waterlogSolid();
    int color();
    AxisAlignedBBRO axisAlignedBB();
    //TODO: ToolTypes
}
