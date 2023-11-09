package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAncientDebrisStack extends ItemStack {
  ItemType<ItemAncientDebrisStack> ANCIENT_DEBRIS_TYPE = ItemTypeBuilder
          .builder(ItemAncientDebrisStack.class)
          .vanillaItem(VanillaItemId.ANCIENT_DEBRIS)
          .build();
}
