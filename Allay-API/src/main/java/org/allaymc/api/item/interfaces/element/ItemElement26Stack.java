package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement26Stack extends ItemStack {
  ItemType<ItemElement26Stack> ELEMENT_26_TYPE = ItemTypeBuilder
          .builder(ItemElement26Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_26)
          .build();
}
