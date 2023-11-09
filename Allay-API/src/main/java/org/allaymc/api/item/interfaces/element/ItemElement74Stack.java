package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement74Stack extends ItemStack {
  ItemType<ItemElement74Stack> ELEMENT_74_TYPE = ItemTypeBuilder
          .builder(ItemElement74Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_74)
          .build();
}
