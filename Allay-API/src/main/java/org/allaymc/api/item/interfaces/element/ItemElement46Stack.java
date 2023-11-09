package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement46Stack extends ItemStack {
  ItemType<ItemElement46Stack> ELEMENT_46_TYPE = ItemTypeBuilder
          .builder(ItemElement46Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_46)
          .build();
}
