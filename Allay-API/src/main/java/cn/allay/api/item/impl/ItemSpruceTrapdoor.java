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
public interface ItemSpruceTrapdoor extends ItemStack {
  ItemType<ItemSpruceTrapdoor> TYPE = ItemTypeBuilder
          .builder(ItemSpruceTrapdoor.class)
          .vanillaItem(VanillaItemId.SPRUCE_TRAPDOOR, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
