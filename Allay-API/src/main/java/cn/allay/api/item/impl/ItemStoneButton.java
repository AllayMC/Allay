package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneButton extends ItemStack {
  ItemType<ItemStoneButton> TYPE = ItemTypeBuilder
          .builder(ItemStoneButton.class)
          .vanillaItem(VanillaItemId.STONE_BUTTON, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
