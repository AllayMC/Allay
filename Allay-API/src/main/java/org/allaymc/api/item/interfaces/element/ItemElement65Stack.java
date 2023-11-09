package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement65Stack extends ItemStack {
  ItemType<ItemElement65Stack> ELEMENT_65_TYPE = ItemTypeBuilder
          .builder(ItemElement65Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_65)
          .build();
}
