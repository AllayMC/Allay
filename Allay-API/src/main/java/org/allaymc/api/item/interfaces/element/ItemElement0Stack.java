package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement0Stack extends ItemStack {
  ItemType<ItemElement0Stack> ELEMENT_0_TYPE = ItemTypeBuilder
          .builder(ItemElement0Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_0)
          .build();
}
