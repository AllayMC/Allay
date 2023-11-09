package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement93Stack extends ItemStack {
  ItemType<ItemElement93Stack> ELEMENT_93_TYPE = ItemTypeBuilder
          .builder(ItemElement93Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_93)
          .build();
}
