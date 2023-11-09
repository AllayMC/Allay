package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeDyeStack extends ItemStack {
  ItemType<ItemOrangeDyeStack> ORANGE_DYE_TYPE = ItemTypeBuilder
          .builder(ItemOrangeDyeStack.class)
          .vanillaItem(VanillaItemId.ORANGE_DYE)
          .build();
}
