package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement89Stack extends ItemStack {
  ItemType<ItemElement89Stack> ELEMENT_89_TYPE = ItemTypeBuilder
          .builder(ItemElement89Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_89)
          .build();
}
