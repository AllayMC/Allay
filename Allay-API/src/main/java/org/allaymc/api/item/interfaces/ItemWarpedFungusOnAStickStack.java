package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFungusOnAStickStack extends ItemStack {
  ItemType<ItemWarpedFungusOnAStickStack> WARPED_FUNGUS_ON_A_STICK_TYPE = ItemTypeBuilder
          .builder(ItemWarpedFungusOnAStickStack.class)
          .vanillaItem(VanillaItemId.WARPED_FUNGUS_ON_A_STICK)
          .build();
}
