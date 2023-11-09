package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement106Stack extends ItemStack {
  ItemType<ItemElement106Stack> ELEMENT_106_TYPE = ItemTypeBuilder
          .builder(ItemElement106Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_106)
          .build();
}
