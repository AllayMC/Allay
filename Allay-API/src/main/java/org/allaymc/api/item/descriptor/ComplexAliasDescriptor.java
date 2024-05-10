package org.allaymc.api.item.descriptor;

import org.allaymc.api.item.type.ItemType;

/**
 * Allay Project 2023/11/26
 * <p>
 * TODO: 目前暂时不知道ComplexAliasDescriptor和DefaultDescriptor有什么区别
 *
 * @author daoge_cmd
 */
public class ComplexAliasDescriptor extends DefaultDescriptor {
    public ComplexAliasDescriptor(ItemType<?> itemType, int meta) {
        super(itemType, meta);
    }

    public ComplexAliasDescriptor(ItemType<?> itemType) {
        super(itemType);
    }
}
