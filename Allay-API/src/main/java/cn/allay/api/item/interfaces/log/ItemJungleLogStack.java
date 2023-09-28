package cn.allay.api.item.interfaces.log;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleLogStack extends ItemStack {
  ItemType<ItemJungleLogStack> JUNGLE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemJungleLogStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_LOG)
          .build();
}
