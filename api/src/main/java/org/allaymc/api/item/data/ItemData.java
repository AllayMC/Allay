package org.allaymc.api.item.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.message.MayContainTrKey;


/**
 * {@code ItemData} holds gameplay properties shared by instances of an item type,
 * including durability limits, armor values, stack limits, and furnace data.
 * The generated builder supplies declared field defaults, while {@code fromJson}
 * delegates resource decoding directly to Gson. Consumers interpret each property
 * when performing their gameplay operation.
 *
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
@Builder
@EqualsAndHashCode
public class ItemData {

    /**
     * {@code GSON} decodes item-data resource entries using Gson's standard mapping
     * without registering custom adapters or applying gameplay validation.
     */
    protected static final Gson GSON = new GsonBuilder().create();

    /**
     * {@code DEFAULT} supplies the shared default data selected by item-type builders.
     * The reference is initialized from builder defaults and can be replaced by callers.
     */
    public static ItemData DEFAULT = ItemData.builder().build();

    /**
     * Armor protection points supplied when this item is equipped.
     */
    @Builder.Default
    protected int armorValue = 0;
    /**
     * {@code enchantValue} supplies enchantability for table and fishing selection.
     * The builder defaults to {@code 0}, which the enchanting-table generator treats
     * as ineligible for generated options.
     */
    @Builder.Default
    protected int enchantValue = 0;
    /**
     * Base attack damage returned by the default item damage calculation.
     */
    @Builder.Default
    protected int attackDamage = 0;
    /**
     * {@code isDamageable} enables default durability setters and broken-item checks.
     * The builder defaults to {@code false}; damageable items need a positive
     * {@code maxDamage} for the broken-item check.
     */
    @Builder.Default
    protected boolean isDamageable = false;
    /**
     * {@code maxDamage} is the durability threshold used for damageable items.
     * The builder defaults to {@code 0}; the default broken-item check rejects
     * nonpositive thresholds rather than treating them as unlimited durability.
     */
    @Builder.Default
    protected int maxDamage = 0;
    /**
     * {@code maxStackSize} limits default stack merging and defines a full stack.
     * The builder defaults to {@code 64}; direct count setters do not enforce this
     * limit.
     */
    @Builder.Default
    protected int maxStackSize = 64;
    /**
     * Armor toughness points included in equipped-armor damage reduction.
     */
    @Builder.Default
    protected int toughnessValue = 0;
    /**
     * {@code furnaceBurnDuration} supplies fuel duration in game ticks. Furnace fuel
     * checks require a positive value, and the furnace truncates it to an integer
     * when setting its burn duration; the builder defaults to {@code 0}.
     */
    @Builder.Default
    protected float furnaceBurnDuration = 0;
    /**
     * {@code furnaceXPMultiplier} supplies expected experience per completed furnace
     * output. The furnace uses the fractional part as the chance of one additional
     * point; the builder defaults to {@code 0}.
     */
    @Builder.Default
    protected float furnaceXPMultiplier = 0;
    /**
     * Item display translation key.
     */
    @MayContainTrKey
    @Builder.Default
    protected String translationKey = "";

    /**
     * Decodes item gameplay data using the shared Gson instance.
     * This method performs no gameplay range checks or builder merge; callers receive
     * the deserialized value, including {@code null} for JSON {@code null}.
     */
    public static ItemData fromJson(String json) {
        return GSON.fromJson(json, ItemData.class);
    }
}
