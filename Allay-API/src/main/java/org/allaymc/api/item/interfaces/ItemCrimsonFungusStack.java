package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonFungusStack extends ItemStack {
  ItemType<ItemCrimsonFungusStack> CRIMSON_FUNGUS_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonFungusStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_FUNGUS)
          .build();
}
