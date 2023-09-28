package cn.allay.api.item.interfaces.log;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveLogStack extends ItemStack {
  ItemType<ItemMangroveLogStack> MANGROVE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemMangroveLogStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_LOG)
          .build();
}
