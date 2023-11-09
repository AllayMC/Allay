package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement20Stack extends ItemStack {
  ItemType<ItemElement20Stack> ELEMENT_20_TYPE = ItemTypeBuilder
          .builder(ItemElement20Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_20)
          .build();
}
