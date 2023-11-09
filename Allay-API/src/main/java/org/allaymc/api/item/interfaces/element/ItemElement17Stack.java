package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement17Stack extends ItemStack {
  ItemType<ItemElement17Stack> ELEMENT_17_TYPE = ItemTypeBuilder
          .builder(ItemElement17Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_17)
          .build();
}
