package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement54Stack extends ItemStack {
  ItemType<ItemElement54Stack> ELEMENT_54_TYPE = ItemTypeBuilder
          .builder(ItemElement54Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_54)
          .build();
}
