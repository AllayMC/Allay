package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteSwordStack extends ItemStack {
  ItemType<ItemNetheriteSwordStack> NETHERITE_SWORD_TYPE = ItemTypeBuilder
          .builder(ItemNetheriteSwordStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_SWORD)
          .build();
}
