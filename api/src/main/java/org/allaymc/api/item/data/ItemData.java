package org.allaymc.api.item.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
@Builder
@EqualsAndHashCode
public class ItemData {

    protected static final Gson GSON = new GsonBuilder().create();

    public static ItemData DEFAULT = ItemData.builder().build();

    @Builder.Default
    protected int armorValue = 0;
    @Builder.Default
    protected int enchantValue = 0;
    @Builder.Default
    protected int attackDamage = 0;
    @Builder.Default
    protected boolean isDamageable = false;
    @Builder.Default
    protected int maxDamage = 0;
    @Builder.Default
    protected int maxStackSize = 64;
    @Builder.Default
    protected int toughnessValue = 0;
    @Builder.Default
    protected float furnaceBurnDuration = 0;
    @Builder.Default
    protected float furnaceXPMultiplier = 0;

    public static ItemData fromJson(String json) {
        return GSON.fromJson(json, ItemData.class);
    }
}
