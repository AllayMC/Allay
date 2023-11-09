package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement67Stack extends ItemStack {
  ItemType<ItemElement67Stack> ELEMENT_67_TYPE = ItemTypeBuilder
          .builder(ItemElement67Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_67)
          .build();
}
