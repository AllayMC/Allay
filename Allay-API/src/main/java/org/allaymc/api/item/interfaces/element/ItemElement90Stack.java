package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement90Stack extends ItemStack {
  ItemType<ItemElement90Stack> ELEMENT_90_TYPE = ItemTypeBuilder
          .builder(ItemElement90Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_90)
          .build();
}
