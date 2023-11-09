package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement114Stack extends ItemStack {
  ItemType<ItemElement114Stack> ELEMENT_114_TYPE = ItemTypeBuilder
          .builder(ItemElement114Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_114)
          .build();
}
