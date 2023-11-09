package org.allaymc.api.item.interfaces.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleSignStack extends ItemStack {
  ItemType<ItemJungleSignStack> JUNGLE_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemJungleSignStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_SIGN)
          .build();
}
