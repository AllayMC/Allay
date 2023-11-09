package org.allaymc.api.item.interfaces;

import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.base.ItemBaseComponentImpl;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAirStack extends ItemStack {
  ItemType<ItemAirStack> AIR_TYPE = ItemTypeBuilder
          .builder(ItemAirStack.class)
          .vanillaItem(VanillaItemId.AIR)
          .addComponent(initInfo -> new ItemAirBaseComponentImpl(), ItemAirBaseComponentImpl.class)
          .build();

    @Slf4j
    class ItemAirBaseComponentImpl extends ItemBaseComponentImpl<ItemAirStack> {

        static SimpleItemStackInitInfo<ItemAirStack> AIR_TYPE_INIT_INFO =
                SimpleItemStackInitInfo
                        .builder()
                        .autoAssignStackNetworkId(false)
                        .build();

        static {
            AIR_TYPE_INIT_INFO.setItemType(AIR_TYPE);
        }

        public ItemAirBaseComponentImpl() {
            super(AIR_TYPE_INIT_INFO);
        }

        @Override
        public void onInitFinish(ComponentInitInfo initInfo) {
            // Do nothing here
        }
    }
}
