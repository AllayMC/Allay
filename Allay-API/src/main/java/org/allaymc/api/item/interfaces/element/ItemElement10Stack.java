package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement10Stack extends ItemStack {
  ItemType<ItemElement10Stack> ELEMENT_10_TYPE = ItemTypeBuilder
          .builder(ItemElement10Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_10)
          .build();
}
