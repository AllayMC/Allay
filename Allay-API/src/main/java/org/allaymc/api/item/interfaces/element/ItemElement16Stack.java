package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement16Stack extends ItemStack {
  ItemType<ItemElement16Stack> ELEMENT_16_TYPE = ItemTypeBuilder
          .builder(ItemElement16Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_16)
          .build();
}
