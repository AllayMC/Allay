package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement44Stack extends ItemStack {
  ItemType<ItemElement44Stack> ELEMENT_44_TYPE = ItemTypeBuilder
          .builder(ItemElement44Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_44)
          .build();
}
