package org.allaymc.api.item.descriptor;

import org.allaymc.api.item.type.ItemType;

/**
 * Allay Project 2023/11/26
 * <p>
 * TODO: Currently unsure of the differences between {@link ComplexAliasDescriptor} and {@link DefaultDescriptor}.
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
