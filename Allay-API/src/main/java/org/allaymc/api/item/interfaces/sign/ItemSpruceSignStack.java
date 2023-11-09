package org.allaymc.api.item.interfaces.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceSignStack extends ItemStack {
  ItemType<ItemSpruceSignStack> SPRUCE_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemSpruceSignStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_SIGN)
          .build();
}
