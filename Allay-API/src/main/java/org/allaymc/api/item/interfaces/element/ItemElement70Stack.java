package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement70Stack extends ItemStack {
  ItemType<ItemElement70Stack> ELEMENT_70_TYPE = ItemTypeBuilder
          .builder(ItemElement70Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_70)
          .build();
}