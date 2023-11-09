package org.allaymc.api.item.interfaces.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkShulkerBoxStack extends ItemStack {
  ItemType<ItemPinkShulkerBoxStack> PINK_SHULKER_BOX_TYPE = ItemTypeBuilder
          .builder(ItemPinkShulkerBoxStack.class)
          .vanillaItem(VanillaItemId.PINK_SHULKER_BOX)
          .build();
}
