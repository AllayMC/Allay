package org.allaymc.api.item.component;

/**
 * @author daoge_cmd
 */
public interface ItemArmorBaseComponent extends ItemBaseComponent {

    ArmorType getArmorType();

    enum ArmorType {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }
}
