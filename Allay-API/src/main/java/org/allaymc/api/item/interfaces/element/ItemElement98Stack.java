package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement98Stack extends ItemStack {
  ItemType<ItemElement98Stack> ELEMENT_98_TYPE = ItemTypeBuilder
          .builder(ItemElement98Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_98)
          .build();
}
