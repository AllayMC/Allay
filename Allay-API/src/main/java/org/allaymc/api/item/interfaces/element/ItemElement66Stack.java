package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement66Stack extends ItemStack {
  ItemType<ItemElement66Stack> ELEMENT_66_TYPE = ItemTypeBuilder
          .builder(ItemElement66Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_66)
          .build();
}
