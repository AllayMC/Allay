package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement52Stack extends ItemStack {
  ItemType<ItemElement52Stack> ELEMENT_52_TYPE = ItemTypeBuilder
          .builder(ItemElement52Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_52)
          .build();
}
