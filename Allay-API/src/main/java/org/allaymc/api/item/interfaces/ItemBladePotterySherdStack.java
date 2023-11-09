package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBladePotterySherdStack extends ItemStack {
  ItemType<ItemBladePotterySherdStack> BLADE_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemBladePotterySherdStack.class)
          .vanillaItem(VanillaItemId.BLADE_POTTERY_SHERD)
          .build();
}
