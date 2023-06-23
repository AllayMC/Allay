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
public interface ItemHardStainedGlassPane extends ItemStack {
  ItemType<ItemHardStainedGlassPane> TYPE = ItemTypeBuilder
          .builder(ItemHardStainedGlassPane.class)
          .vanillaItem(VanillaItemId.HARD_STAINED_GLASS_PANE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
