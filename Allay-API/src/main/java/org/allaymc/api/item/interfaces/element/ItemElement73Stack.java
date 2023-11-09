package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement73Stack extends ItemStack {
  ItemType<ItemElement73Stack> ELEMENT_73_TYPE = ItemTypeBuilder
          .builder(ItemElement73Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_73)
          .build();
}
