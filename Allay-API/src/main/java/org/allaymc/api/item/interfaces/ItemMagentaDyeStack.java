package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaDyeStack extends ItemStack {
  ItemType<ItemMagentaDyeStack> MAGENTA_DYE_TYPE = ItemTypeBuilder
          .builder(ItemMagentaDyeStack.class)
          .vanillaItem(VanillaItemId.MAGENTA_DYE)
          .build();
}
