package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement75Stack extends ItemStack {
  ItemType<ItemElement75Stack> ELEMENT_75_TYPE = ItemTypeBuilder
          .builder(ItemElement75Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_75)
          .build();
}
