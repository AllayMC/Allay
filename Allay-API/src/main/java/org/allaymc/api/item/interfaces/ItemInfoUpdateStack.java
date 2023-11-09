package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfoUpdateStack extends ItemStack {
  ItemType<ItemInfoUpdateStack> INFO_UPDATE_TYPE = ItemTypeBuilder
          .builder(ItemInfoUpdateStack.class)
          .vanillaItem(VanillaItemId.INFO_UPDATE)
          .build();
}
