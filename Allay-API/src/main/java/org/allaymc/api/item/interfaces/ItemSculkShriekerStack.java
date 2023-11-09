package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkShriekerStack extends ItemStack {
  ItemType<ItemSculkShriekerStack> SCULK_SHRIEKER_TYPE = ItemTypeBuilder
          .builder(ItemSculkShriekerStack.class)
          .vanillaItem(VanillaItemId.SCULK_SHRIEKER)
          .build();
}
