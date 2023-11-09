package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement82Stack extends ItemStack {
  ItemType<ItemElement82Stack> ELEMENT_82_TYPE = ItemTypeBuilder
          .builder(ItemElement82Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_82)
          .build();
}
