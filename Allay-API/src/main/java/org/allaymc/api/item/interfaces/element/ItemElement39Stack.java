package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement39Stack extends ItemStack {
  ItemType<ItemElement39Stack> ELEMENT_39_TYPE = ItemTypeBuilder
          .builder(ItemElement39Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_39)
          .build();
}
