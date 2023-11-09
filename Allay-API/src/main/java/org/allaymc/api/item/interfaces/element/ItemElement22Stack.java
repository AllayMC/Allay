package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement22Stack extends ItemStack {
  ItemType<ItemElement22Stack> ELEMENT_22_TYPE = ItemTypeBuilder
          .builder(ItemElement22Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_22)
          .build();
}
