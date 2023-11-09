package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderEyeStack extends ItemStack {
  ItemType<ItemEnderEyeStack> ENDER_EYE_TYPE = ItemTypeBuilder
          .builder(ItemEnderEyeStack.class)
          .vanillaItem(VanillaItemId.ENDER_EYE)
          .build();
}
