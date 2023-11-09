package org.allaymc.api.item.interfaces.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakSignStack extends ItemStack {
  ItemType<ItemDarkOakSignStack> DARK_OAK_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakSignStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_SIGN)
          .build();
}
