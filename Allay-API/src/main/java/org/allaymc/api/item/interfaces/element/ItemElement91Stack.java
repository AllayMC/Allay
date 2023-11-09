package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement91Stack extends ItemStack {
  ItemType<ItemElement91Stack> ELEMENT_91_TYPE = ItemTypeBuilder
          .builder(ItemElement91Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_91)
          .build();
}
