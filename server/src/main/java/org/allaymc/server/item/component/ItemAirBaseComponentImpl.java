package org.allaymc.server.item.component;

import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.server.component.annotation.OnInitFinish;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * @author daoge_cmd
 */
public class ItemAirBaseComponentImpl extends ItemBaseComponentImpl {

    public static ItemStackInitInfo AIR_TYPE_INIT_INFO =
            ItemStackInitInfo
                    .builder()
                    .assignUniqueId(false)
                    .build();

    static {
        AIR_TYPE_INIT_INFO.setItemType(AIR);
    }

    public ItemAirBaseComponentImpl() {
        super(AIR_TYPE_INIT_INFO);
    }

    @Override
    @OnInitFinish
    public void onInitFinish(ItemStackInitInfo initInfo) {
        // Do nothing here
    }
}
