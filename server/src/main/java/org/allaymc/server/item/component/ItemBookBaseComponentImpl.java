package org.allaymc.server.item.component;

import org.allaymc.api.item.initinfo.ItemStackInitInfo;

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
