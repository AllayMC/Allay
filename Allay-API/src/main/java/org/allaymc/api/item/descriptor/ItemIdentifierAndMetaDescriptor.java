package org.allaymc.api.item.descriptor;

import lombok.Getter;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@Getter
public class ItemIdentifierAndMetaDescriptor extends ItemIdentifierDescriptor {
    protected int meta;

    public ItemIdentifierAndMetaDescriptor(Identifier identifier, int meta) {
        super(identifier);
        this.meta = meta;
    }

    @Override
    public boolean match(ItemStack itemStack) {
        return super.match(itemStack) && itemStack.getMeta() == meta;
    }
}
