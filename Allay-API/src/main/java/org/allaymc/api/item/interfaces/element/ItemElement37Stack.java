package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement37Stack extends ItemStack {
  ItemType<ItemElement37Stack> ELEMENT_37_TYPE = ItemTypeBuilder
          .builder(ItemElement37Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_37)
          .build();
}
