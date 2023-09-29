package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSheafPotterySherdStack extends ItemStack {
  ItemType<ItemSheafPotterySherdStack> SHEAF_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemSheafPotterySherdStack.class)
          .vanillaItem(VanillaItemId.SHEAF_POTTERY_SHERD)
          .build();
}
