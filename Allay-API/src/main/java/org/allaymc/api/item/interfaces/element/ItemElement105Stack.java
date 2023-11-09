package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement105Stack extends ItemStack {
  ItemType<ItemElement105Stack> ELEMENT_105_TYPE = ItemTypeBuilder
          .builder(ItemElement105Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_105)
          .build();
}
