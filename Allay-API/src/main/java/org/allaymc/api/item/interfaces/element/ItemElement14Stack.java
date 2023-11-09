package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement14Stack extends ItemStack {
  ItemType<ItemElement14Stack> ELEMENT_14_TYPE = ItemTypeBuilder
          .builder(ItemElement14Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_14)
          .build();
}
