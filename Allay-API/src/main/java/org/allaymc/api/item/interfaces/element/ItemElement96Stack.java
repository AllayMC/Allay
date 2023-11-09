package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement96Stack extends ItemStack {
  ItemType<ItemElement96Stack> ELEMENT_96_TYPE = ItemTypeBuilder
          .builder(ItemElement96Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_96)
          .build();
}
