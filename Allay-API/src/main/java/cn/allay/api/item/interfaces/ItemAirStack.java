package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
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
          .addComponent(initInfo -> {
            if (initInfo.stackNetworkId() != EMPTY_STACK_NETWORK_ID)
              throw new IllegalArgumentException("Air stack cannot have a stack network id");
            return new ItemBaseComponentImpl<>(initInfo);
          }, ItemBaseComponentImpl.class)
          .build();
}
