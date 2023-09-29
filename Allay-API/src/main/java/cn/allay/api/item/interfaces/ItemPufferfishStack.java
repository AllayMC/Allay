package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPufferfishStack extends ItemStack {
  ItemType<ItemPufferfishStack> PUFFERFISH_TYPE = ItemTypeBuilder
          .builder(ItemPufferfishStack.class)
          .vanillaItem(VanillaItemId.PUFFERFISH)
          .build();
}
