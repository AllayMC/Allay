package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement100Stack extends ItemStack {
  ItemType<ItemElement100Stack> ELEMENT_100_TYPE = ItemTypeBuilder
          .builder(ItemElement100Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_100)
          .build();
}
