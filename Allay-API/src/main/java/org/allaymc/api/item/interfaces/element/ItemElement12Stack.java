package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement12Stack extends ItemStack {
  ItemType<ItemElement12Stack> ELEMENT_12_TYPE = ItemTypeBuilder
          .builder(ItemElement12Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_12)
          .build();
}
