package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement62Stack extends ItemStack {
  ItemType<ItemElement62Stack> ELEMENT_62_TYPE = ItemTypeBuilder
          .builder(ItemElement62Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_62)
          .build();
}
