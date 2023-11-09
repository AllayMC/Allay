package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemGlowFrameStack extends ItemStack {
  ItemType<ItemItemGlowFrameStack> ITEM_GLOW_FRAME_TYPE = ItemTypeBuilder
          .builder(ItemItemGlowFrameStack.class)
          .vanillaItem(VanillaItemId.ITEM_GLOW_FRAME)
          .build();
}
