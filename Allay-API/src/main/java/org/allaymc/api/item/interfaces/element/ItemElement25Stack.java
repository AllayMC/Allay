package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement25Stack extends ItemStack {
  ItemType<ItemElement25Stack> ELEMENT_25_TYPE = ItemTypeBuilder
          .builder(ItemElement25Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_25)
          .build();
}
