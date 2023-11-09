package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemFrameStack extends ItemStack {
  ItemType<ItemItemFrameStack> ITEM_FRAME_TYPE = ItemTypeBuilder
          .builder(ItemItemFrameStack.class)
          .vanillaItem(VanillaItemId.ITEM_FRAME)
          .build();
}
