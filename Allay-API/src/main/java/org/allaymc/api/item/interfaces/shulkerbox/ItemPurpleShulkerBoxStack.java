package org.allaymc.api.item.interfaces.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleShulkerBoxStack extends ItemStack {
  ItemType<ItemPurpleShulkerBoxStack> PURPLE_SHULKER_BOX_TYPE = ItemTypeBuilder
          .builder(ItemPurpleShulkerBoxStack.class)
          .vanillaItem(VanillaItemId.PURPLE_SHULKER_BOX)
          .build();
}
