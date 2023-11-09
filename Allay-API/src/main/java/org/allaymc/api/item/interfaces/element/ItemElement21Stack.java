package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement21Stack extends ItemStack {
  ItemType<ItemElement21Stack> ELEMENT_21_TYPE = ItemTypeBuilder
          .builder(ItemElement21Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_21)
          .build();
}
