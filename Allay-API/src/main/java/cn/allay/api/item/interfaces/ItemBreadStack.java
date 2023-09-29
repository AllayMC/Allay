package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBreadStack extends ItemStack {
  ItemType<ItemBreadStack> BREAD_TYPE = ItemTypeBuilder
          .builder(ItemBreadStack.class)
          .vanillaItem(VanillaItemId.BREAD)
          .build();
}
