package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement71Stack extends ItemStack {
  ItemType<ItemElement71Stack> ELEMENT_71_TYPE = ItemTypeBuilder
          .builder(ItemElement71Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_71)
          .build();
}
