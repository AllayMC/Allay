package cn.allay.api.item.interfaces.log;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedMangroveLogStack extends ItemStack {
  ItemType<ItemStrippedMangroveLogStack> STRIPPED_MANGROVE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemStrippedMangroveLogStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_MANGROVE_LOG)
          .build();
}
