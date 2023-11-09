package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement116Stack extends ItemStack {
  ItemType<ItemElement116Stack> ELEMENT_116_TYPE = ItemTypeBuilder
          .builder(ItemElement116Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_116)
          .build();
}
