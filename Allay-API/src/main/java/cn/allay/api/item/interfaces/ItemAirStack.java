package cn.allay.api.item.interfaces;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
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
