package org.allaymc.api.item.data;

/**
 * {@code ArmorType} identifies the equipment slot occupied by wearable armor.
 * The declaration order matches helmet, chestplate, leggings, and boots indices
 * in armor containers, so wearable placement and slot validation can use the
 * same value to select a destination.
 *
 * @author daoge_cmd
 */
public enum ArmorType {
    /**
     * Head armor equipment slot.
     */
    HELMET,
    /**
     * Torso armor equipment slot.
     */
    CHESTPLATE,
    /**
     * Leg armor equipment slot.
     */
    LEGGINGS,
    /**
     * Foot armor equipment slot.
     */
    BOOTS
}
