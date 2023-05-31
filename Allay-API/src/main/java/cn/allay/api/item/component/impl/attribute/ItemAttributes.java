package cn.allay.api.item.component.impl.attribute;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
@Getter
@Accessors(fluent = true)
@Builder
@EqualsAndHashCode
public class ItemAttributes {
    protected static Gson SERIALIZER = new Gson();

    public static ItemAttributes DEFAULT = ItemAttributes.builder().build();

    public static ItemAttributes of(String json) {
        return SERIALIZER.fromJson(json, ItemAttributes.class);
    }

    @Builder.Default
    protected int armorValue = 0;
    @Builder.Default
    protected int attackDamage = 0;
    //auxValuesDescription
    @Builder.Default
    protected boolean canBeCharged = false;
    @Builder.Default
    protected boolean canBeDepleted = false;
    @Builder.Default
    protected boolean canDestroyInCreative = false;
    @Builder.Default
    protected boolean canUseOnSimTick = false;
    @Builder.Default
    protected int cooldownTime = 0;
    @Builder.Default
    protected String cooldownType = "";
    @Builder.Default
    protected int creativeCategory = 0;
    @Builder.Default
    protected String creativeGroup = "";
    @Builder.Default
    protected int id = Integer.MAX_VALUE;
    @Builder.Default
    protected boolean isArmor = false;
    @Builder.Default
    protected boolean isBlockPlanterItem = false;
    @Builder.Default
    protected boolean isDamageable = false;
    @Builder.Default
    protected boolean isDye = false;
    @Builder.Default
    protected boolean isDyeable = false;
    @Builder.Default
    protected boolean isElytra = false;
    @Builder.Default
    protected boolean isFertilizer = false;
    @Builder.Default
    protected boolean isFood = false;
    @Builder.Default
    protected boolean isThrowable = false;
    @Builder.Default
    protected boolean isUseable = false;
    @Builder.Default
    protected String itemColorName = "";
    @Builder.Default
    protected int itemColorRGB = 0;
    @Builder.Default
    protected int maxDamage = 0;
    @Builder.Default
    protected String name = "";
    @Builder.Default
    protected int toughnessValue = 0;
    @Builder.Default
    protected float viewDamping = 1.0f;
}
