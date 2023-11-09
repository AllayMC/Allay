package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement41Stack extends ItemStack {
  ItemType<ItemElement41Stack> ELEMENT_41_TYPE = ItemTypeBuilder
          .builder(ItemElement41Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_41)
          .build();
}
