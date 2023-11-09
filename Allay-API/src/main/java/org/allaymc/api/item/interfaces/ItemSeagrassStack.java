package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeagrassStack extends ItemStack {
  ItemType<ItemSeagrassStack> SEAGRASS_TYPE = ItemTypeBuilder
          .builder(ItemSeagrassStack.class)
          .vanillaItem(VanillaItemId.SEAGRASS)
          .build();
}
