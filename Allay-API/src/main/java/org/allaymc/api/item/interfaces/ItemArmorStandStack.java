package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArmorStandStack extends ItemStack {
  ItemType<ItemArmorStandStack> ARMOR_STAND_TYPE = ItemTypeBuilder
          .builder(ItemArmorStandStack.class)
          .vanillaItem(VanillaItemId.ARMOR_STAND)
          .build();
}
