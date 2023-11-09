package org.allaymc.api.item.interfaces.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeWoolStack extends ItemStack {
  ItemType<ItemLimeWoolStack> LIME_WOOL_TYPE = ItemTypeBuilder
          .builder(ItemLimeWoolStack.class)
          .vanillaItem(VanillaItemId.LIME_WOOL)
          .build();
}
