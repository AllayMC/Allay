package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement115Stack extends ItemStack {
  ItemType<ItemElement115Stack> ELEMENT_115_TYPE = ItemTypeBuilder
          .builder(ItemElement115Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_115)
          .build();
}
