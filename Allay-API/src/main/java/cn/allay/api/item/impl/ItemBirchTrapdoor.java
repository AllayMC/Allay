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
public interface ItemBirchTrapdoor extends ItemStack {
  ItemType<ItemBirchTrapdoor> TYPE = ItemTypeBuilder
          .builder(ItemBirchTrapdoor.class)
          .vanillaItem(VanillaItemId.BIRCH_TRAPDOOR, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
