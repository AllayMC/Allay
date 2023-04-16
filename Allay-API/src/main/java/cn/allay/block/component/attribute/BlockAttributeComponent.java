package cn.allay.block.component.attribute;

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
public interface BlockAttributeComponent {

    Gson SERIALIZER = new GsonBuilder()
            .registerTypeAdapter(AxisAlignedBBRO.class, (JsonDeserializer<Object>) (json, typeOfT, context) -> {
                var numbers = StringUtils.fastSplit(json.getAsString(), ",").stream().map(Float::valueOf).toList();
                return AxisAlignedBBRO.of(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4), numbers.get(5));
            })
            .create();

    AxisAlignedBBRO aabb();

    boolean blocksPrecipitation();

    boolean canBeMovingBlock();

    boolean canContainLiquid();

    boolean breaksFallingBlocks();

    int burnAbility();

    int burnChance();

    boolean canBeBrokenFromFalling();

    int color();

    float explosionResistance();

    float friction();

    float hardness();

    boolean hasBlockEntity();

    boolean hasComparatorSignal();

    boolean isAlwaysDestroyable();

    boolean isContainerBlock();

    boolean isLiquid();

    boolean isMotionBlockingBlock();

    boolean isPowerSource();

    boolean isSolid();

    boolean isSolidBlocking();

    boolean isUnbreakable();

    boolean isWaterBlocking();

    boolean flammable();

    int light();

    int lightEmission();

    boolean pushesUpFallingBlocks();

    boolean superHot();

    float thickness();

    float translucency();

    boolean waterSpreadCausesSpawn();
}
