package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalStack extends ItemStack {
  ItemType<ItemCoalStack> COAL_TYPE = ItemTypeBuilder
          .builder(ItemCoalStack.class)
          .vanillaItem(VanillaItemId.COAL)
          .build();
}
