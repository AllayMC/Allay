package org.allaymc.server.item.component.book;

import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.api.item.ItemStackInitInfo;

/**
 * @author daoge_cmd
 */
public class ItemBookBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemBookBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean isAllEnchantmentsAvailableInEnchantTable() {
        return true;
    }
}
