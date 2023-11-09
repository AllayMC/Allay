package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement30Stack extends ItemStack {
  ItemType<ItemElement30Stack> ELEMENT_30_TYPE = ItemTypeBuilder
          .builder(ItemElement30Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_30)
          .build();
}
