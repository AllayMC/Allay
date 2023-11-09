package org.allaymc.api.item.interfaces.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySignStack extends ItemStack {
  ItemType<ItemCherrySignStack> CHERRY_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemCherrySignStack.class)
          .vanillaItem(VanillaItemId.CHERRY_SIGN)
          .build();
}
