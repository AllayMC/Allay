package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemKelpStack extends ItemStack {
  ItemType<ItemItemKelpStack> ITEM_KELP_TYPE = ItemTypeBuilder
          .builder(ItemItemKelpStack.class)
          .vanillaItem(VanillaItemId.ITEM_KELP)
          .build();
}
