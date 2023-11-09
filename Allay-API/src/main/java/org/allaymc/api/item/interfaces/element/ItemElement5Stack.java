package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement5Stack extends ItemStack {
  ItemType<ItemElement5Stack> ELEMENT_5_TYPE = ItemTypeBuilder
          .builder(ItemElement5Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_5)
          .build();
}
