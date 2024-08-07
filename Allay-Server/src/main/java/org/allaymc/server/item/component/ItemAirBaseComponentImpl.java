package org.allaymc.server.item.component;

import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class ItemAirBaseComponentImpl extends ItemBaseComponentImpl<ItemAirStack> {

    public static SimpleItemStackInitInfo<ItemAirStack> AIR_TYPE_INIT_INFO =
            SimpleItemStackInitInfo
                    .builder()
                    .autoAssignStackNetworkId(false)
                    .build();

    static {
        AIR_TYPE_INIT_INFO.setItemType(AIR);
    }

    public ItemAirBaseComponentImpl() {
        super(AIR_TYPE_INIT_INFO);
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        // Do nothing here
    }
}
