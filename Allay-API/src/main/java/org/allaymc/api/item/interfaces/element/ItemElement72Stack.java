package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement72Stack extends ItemStack {
  ItemType<ItemElement72Stack> ELEMENT_72_TYPE = ItemTypeBuilder
          .builder(ItemElement72Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_72)
          .build();
}
