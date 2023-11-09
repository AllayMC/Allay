package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement18Stack extends ItemStack {
  ItemType<ItemElement18Stack> ELEMENT_18_TYPE = ItemTypeBuilder
          .builder(ItemElement18Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_18)
          .build();
}
