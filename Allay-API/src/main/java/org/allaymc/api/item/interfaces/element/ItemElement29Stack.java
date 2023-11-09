package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement29Stack extends ItemStack {
  ItemType<ItemElement29Stack> ELEMENT_29_TYPE = ItemTypeBuilder
          .builder(ItemElement29Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_29)
          .build();
}
