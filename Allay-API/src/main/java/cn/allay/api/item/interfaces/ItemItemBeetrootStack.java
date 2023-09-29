package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBeetrootStack extends ItemStack {
  ItemType<ItemItemBeetrootStack> ITEM_BEETROOT_TYPE = ItemTypeBuilder
          .builder(ItemItemBeetrootStack.class)
          .vanillaItem(VanillaItemId.ITEM_BEETROOT)
          .build();
}
