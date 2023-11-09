package org.allaymc.api.item.interfaces.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchButtonStack extends ItemStack {
  ItemType<ItemBirchButtonStack> BIRCH_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemBirchButtonStack.class)
          .vanillaItem(VanillaItemId.BIRCH_BUTTON)
          .build();
}
