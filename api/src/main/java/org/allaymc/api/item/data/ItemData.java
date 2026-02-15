package org.allaymc.api.item.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.message.MayContainTrKey;


/**
 * Represents the data associated with an item, including properties such as armor value, enchantability, and damage.
 * <p>
 * This class is designed to be immutable and provides a builder pattern for easy construction of instances.
 * <p>
 * It includes a default instance that can be used as a template or base for creating new `ItemData` objects.
 * <p>
 * Additionally, it supports conversion from a JSON string to an `ItemData` object using Gson.
 *
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
@Builder
@EqualsAndHashCode
public class ItemData {

    protected static final Gson GSON = new GsonBuilder().create();

    public static ItemData DEFAULT = ItemData.builder().build();

    /**
     * Represents the base armor value of an item. This value is used to determine the protection level provided by the item
     * when worn as armor.
     * <p>
     * The default value is 0, indicating no armor protection.
     */
    @Builder.Default
    protected int armorValue = 0;
    /**
     * Represents the enchant ability value of an item. This value is used to determine the quality and variety of enchantment
     * options available when using an enchanting table. A higher enchant value results in better enchantment options.
     * <p>
     * The default value is 0, indicating that the item cannot be enchanted.
     */
    @Builder.Default
    protected int enchantValue = 0;
    /**
     * Represents the base attack damage of an item. This value is used to determine the amount of damage the item can inflict
     * when used as a weapon.
     * <p>
     * The default value is 0, indicating that the item does not deal any additional damage.
     */
    @Builder.Default
    protected int attackDamage = 0;
    /**
     * Indicates whether the item can be damaged and lose durability.
     * <p>
     * If set to true, the item can receive damage and may eventually break. If false, the item is not subject to damage and
     * will not lose durability.
     * <p>
     * The default value is false, meaning the item is not damageable by default.
     */
    @Builder.Default
    protected boolean isDamageable = false;
    /**
     * Represents the maximum amount of damage this item can sustain before it is destroyed.
     * <p>
     * The default value is 0, indicating that the item does not have a limit on how much damage it can take. If the item is
     * not intended to be damageable,
     * this value should remain at 0.
     */
    @Builder.Default
    protected int maxDamage = 0;
    /**
     * Represents the maximum number of items that can be stacked in a single inventory slot.
     * <p>
     * The default value is 64, which is the standard stack size for most items in Minecraft. This value can be adjusted to
     * limit or increase the stack size for specific items.
     */
    @Builder.Default
    protected int maxStackSize = 64;
    /**
     * Represents the toughness value of an item. This value is used to determine the additional protection level provided by
     * the item when worn as armor.
     * <p>
     * The default value is 0, indicating no additional toughness.
     */
    @Builder.Default
    protected int toughnessValue = 0;
    /**
     * Represents the duration in seconds that an item can burn in a furnace.
     * <p>
     * This value is used to determine how long an item will fuel a furnace. A value of 0 indicates
     * that the item cannot be used as fuel.
     */
    @Builder.Default
    protected float furnaceBurnDuration = 0;
    /**
     * Represents the experience multiplier that will be given to the player when an item is smelted in the furnace. This
     * multiplier directly affects the total amount of experience the player ends up earning
     * <p>
     * The default value is 0, indicating no experience the player will get when the item is smelted in the furnace.
     */
    @Builder.Default
    protected float furnaceXPMultiplier = 0;
    /**
     * The translation key of the item.
     */
    @MayContainTrKey
    @Builder.Default
    protected String translationKey = "";

    /**
     * Converts a JSON string into an ItemData object.
     *
     * @param json the JSON string to be converted into an ItemData object
     * @return an ItemData object representing the deserialized JSON
     */
    public static ItemData fromJson(String json) {
        return GSON.fromJson(json, ItemData.class);
    }
}
