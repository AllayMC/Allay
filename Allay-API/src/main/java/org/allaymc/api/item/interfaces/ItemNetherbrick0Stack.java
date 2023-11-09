package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherbrick0Stack extends ItemStack {
  ItemType<ItemNetherbrick0Stack> NETHERBRICK_TYPE = ItemTypeBuilder
          .builder(ItemNetherbrick0Stack.class)
          .vanillaItem(VanillaItemId.NETHERBRICK)
          .build();
}
