package org.allaymc.api.item.interfaces.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowShulkerBoxStack extends ItemStack {
  ItemType<ItemYellowShulkerBoxStack> YELLOW_SHULKER_BOX_TYPE = ItemTypeBuilder
          .builder(ItemYellowShulkerBoxStack.class)
          .vanillaItem(VanillaItemId.YELLOW_SHULKER_BOX)
          .build();
}
