package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulFireStack extends ItemStack {
  ItemType<ItemSoulFireStack> SOUL_FIRE_TYPE = ItemTypeBuilder
          .builder(ItemSoulFireStack.class)
          .vanillaItem(VanillaItemId.SOUL_FIRE)
          .build();
}
