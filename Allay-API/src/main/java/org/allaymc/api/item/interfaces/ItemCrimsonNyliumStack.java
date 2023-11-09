package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonNyliumStack extends ItemStack {
  ItemType<ItemCrimsonNyliumStack> CRIMSON_NYLIUM_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonNyliumStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_NYLIUM)
          .build();
}
