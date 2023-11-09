package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement35Stack extends ItemStack {
  ItemType<ItemElement35Stack> ELEMENT_35_TYPE = ItemTypeBuilder
          .builder(ItemElement35Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_35)
          .build();
}
