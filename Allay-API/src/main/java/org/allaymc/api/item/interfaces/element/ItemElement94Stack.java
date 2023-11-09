package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement94Stack extends ItemStack {
  ItemType<ItemElement94Stack> ELEMENT_94_TYPE = ItemTypeBuilder
          .builder(ItemElement94Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_94)
          .build();
}
