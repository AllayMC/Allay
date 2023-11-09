package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement24Stack extends ItemStack {
  ItemType<ItemElement24Stack> ELEMENT_24_TYPE = ItemTypeBuilder
          .builder(ItemElement24Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_24)
          .build();
}
