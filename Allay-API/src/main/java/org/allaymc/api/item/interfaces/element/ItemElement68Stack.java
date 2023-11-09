package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement68Stack extends ItemStack {
  ItemType<ItemElement68Stack> ELEMENT_68_TYPE = ItemTypeBuilder
          .builder(ItemElement68Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_68)
          .build();
}
