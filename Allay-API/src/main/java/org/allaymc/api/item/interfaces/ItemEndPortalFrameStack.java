package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndPortalFrameStack extends ItemStack {
  ItemType<ItemEndPortalFrameStack> END_PORTAL_FRAME_TYPE = ItemTypeBuilder
          .builder(ItemEndPortalFrameStack.class)
          .vanillaItem(VanillaItemId.END_PORTAL_FRAME)
          .build();
}
