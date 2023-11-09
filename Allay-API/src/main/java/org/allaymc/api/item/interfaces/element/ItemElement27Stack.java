package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement27Stack extends ItemStack {
  ItemType<ItemElement27Stack> ELEMENT_27_TYPE = ItemTypeBuilder
          .builder(ItemElement27Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_27)
          .build();
}
