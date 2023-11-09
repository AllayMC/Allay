package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTwistingVinesStack extends ItemStack {
  ItemType<ItemTwistingVinesStack> TWISTING_VINES_TYPE = ItemTypeBuilder
          .builder(ItemTwistingVinesStack.class)
          .vanillaItem(VanillaItemId.TWISTING_VINES)
          .build();
}
