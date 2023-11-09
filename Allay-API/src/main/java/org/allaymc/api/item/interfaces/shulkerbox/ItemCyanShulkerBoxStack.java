package org.allaymc.api.item.interfaces.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanShulkerBoxStack extends ItemStack {
  ItemType<ItemCyanShulkerBoxStack> CYAN_SHULKER_BOX_TYPE = ItemTypeBuilder
          .builder(ItemCyanShulkerBoxStack.class)
          .vanillaItem(VanillaItemId.CYAN_SHULKER_BOX)
          .build();
}
