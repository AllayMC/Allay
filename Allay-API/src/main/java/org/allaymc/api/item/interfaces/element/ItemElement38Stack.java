package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement38Stack extends ItemStack {
  ItemType<ItemElement38Stack> ELEMENT_38_TYPE = ItemTypeBuilder
          .builder(ItemElement38Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_38)
          .build();
}
