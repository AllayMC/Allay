package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeepingVinesStack extends ItemStack {
  ItemType<ItemWeepingVinesStack> WEEPING_VINES_TYPE = ItemTypeBuilder
          .builder(ItemWeepingVinesStack.class)
          .vanillaItem(VanillaItemId.WEEPING_VINES)
          .build();
}
