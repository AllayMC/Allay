package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement110Stack extends ItemStack {
  ItemType<ItemElement110Stack> ELEMENT_110_TYPE = ItemTypeBuilder
          .builder(ItemElement110Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_110)
          .build();
}
