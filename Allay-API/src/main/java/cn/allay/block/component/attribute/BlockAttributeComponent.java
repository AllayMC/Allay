package cn.allay.block.component.attribute;

import cn.allay.component.annotation.Inject;
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

    @Inject
    AxisAlignedBBRO aabb();

    @Inject
    boolean blocksPrecipitation();

    @Inject
    boolean canBeMovingBlock();

    @Inject
    boolean canContainLiquid();

    @Inject
    boolean breaksFallingBlocks();

    @Inject
    int burnAbility();

    @Inject
    int burnChance();

    @Inject
    boolean canBeBrokenFromFalling();

    @Inject
    int color();

    @Inject
    float explosionResistance();

    @Inject
    float friction();

    @Inject
    float hardness();

    @Inject
    boolean hasBlockEntity();

    @Inject
    boolean hasComparatorSignal();

    @Inject
    boolean isAlwaysDestroyable();

    @Inject
    boolean isContainerBlock();

    @Inject
    boolean isLiquid();

    @Inject
    boolean isMotionBlockingBlock();

    @Inject
    boolean isPowerSource();

    @Inject
    boolean isSolid();

    @Inject
    boolean isSolidBlocking();

    @Inject
    boolean isUnbreakable();

    @Inject
    boolean isWaterBlocking();

    @Inject
    boolean flammable();

    @Inject
    int light();

    @Inject
    int lightEmission();

    @Inject
    boolean pushesUpFallingBlocks();

    @Inject
    boolean superHot();

    @Inject
    float thickness();

    @Inject
    float translucency();

    @Inject
    boolean waterSpreadCausesSpawn();
}
