package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement85Stack extends ItemStack {
  ItemType<ItemElement85Stack> ELEMENT_85_TYPE = ItemTypeBuilder
          .builder(ItemElement85Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_85)
          .build();
}
