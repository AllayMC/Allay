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
public interface ItemDiamondHorseArmor extends ItemStack {
  ItemType<ItemDiamondHorseArmor> TYPE = ItemTypeBuilder
          .builder(ItemDiamondHorseArmor.class)
          .vanillaItem(VanillaItemId.DIAMOND_HORSE_ARMOR, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
