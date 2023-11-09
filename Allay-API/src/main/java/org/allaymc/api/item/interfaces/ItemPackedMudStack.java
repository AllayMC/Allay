package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPackedMudStack extends ItemStack {
  ItemType<ItemPackedMudStack> PACKED_MUD_TYPE = ItemTypeBuilder
          .builder(ItemPackedMudStack.class)
          .vanillaItem(VanillaItemId.PACKED_MUD)
          .build();
}
