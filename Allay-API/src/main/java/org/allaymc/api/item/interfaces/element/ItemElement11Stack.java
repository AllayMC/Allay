package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement11Stack extends ItemStack {
  ItemType<ItemElement11Stack> ELEMENT_11_TYPE = ItemTypeBuilder
          .builder(ItemElement11Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_11)
          .build();
}
