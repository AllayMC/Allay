package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarrotStack extends ItemStack {
  ItemType<ItemCarrotStack> CARROT_TYPE = ItemTypeBuilder
          .builder(ItemCarrotStack.class)
          .vanillaItem(VanillaItemId.CARROT)
          .build();
}
