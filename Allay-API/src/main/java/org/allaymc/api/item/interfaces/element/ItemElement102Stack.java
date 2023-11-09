package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement102Stack extends ItemStack {
  ItemType<ItemElement102Stack> ELEMENT_102_TYPE = ItemTypeBuilder
          .builder(ItemElement102Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_102)
          .build();
}
