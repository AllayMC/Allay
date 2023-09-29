package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMournerPotterySherdStack extends ItemStack {
  ItemType<ItemMournerPotterySherdStack> MOURNER_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemMournerPotterySherdStack.class)
          .vanillaItem(VanillaItemId.MOURNER_POTTERY_SHERD)
          .build();
}
