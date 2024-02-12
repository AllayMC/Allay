package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWolfArmorStack extends ItemStack {
  ItemType<ItemWolfArmorStack> WOLF_ARMOR_TYPE = ItemTypeBuilder
          .builder(ItemWolfArmorStack.class)
          .vanillaItem(VanillaItemId.WOLF_ARMOR)
          .build();
}
