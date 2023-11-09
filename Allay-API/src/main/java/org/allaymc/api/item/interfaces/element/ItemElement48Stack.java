package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement48Stack extends ItemStack {
  ItemType<ItemElement48Stack> ELEMENT_48_TYPE = ItemTypeBuilder
          .builder(ItemElement48Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_48)
          .build();
}
