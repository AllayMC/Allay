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
public interface ItemSponge extends ItemStack {
  ItemType<ItemSponge> TYPE = ItemTypeBuilder
          .builder(ItemSponge.class)
          .vanillaItem(VanillaItemId.SPONGE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
