package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement55Stack extends ItemStack {
  ItemType<ItemElement55Stack> ELEMENT_55_TYPE = ItemTypeBuilder
          .builder(ItemElement55Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_55)
          .build();
}
