package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement95Stack extends ItemStack {
  ItemType<ItemElement95Stack> ELEMENT_95_TYPE = ItemTypeBuilder
          .builder(ItemElement95Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_95)
          .build();
}
