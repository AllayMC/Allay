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
public interface ItemCarrotOnAStick extends ItemStack {
  ItemType<ItemCarrotOnAStick> TYPE = ItemTypeBuilder
          .builder(ItemCarrotOnAStick.class)
          .vanillaItem(VanillaItemId.CARROT_ON_A_STICK, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
