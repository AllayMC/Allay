package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement42Stack extends ItemStack {
  ItemType<ItemElement42Stack> ELEMENT_42_TYPE = ItemTypeBuilder
          .builder(ItemElement42Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_42)
          .build();
}
