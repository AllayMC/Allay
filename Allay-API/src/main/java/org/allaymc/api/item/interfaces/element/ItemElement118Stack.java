package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement118Stack extends ItemStack {
  ItemType<ItemElement118Stack> ELEMENT_118_TYPE = ItemTypeBuilder
          .builder(ItemElement118Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_118)
          .build();
}
