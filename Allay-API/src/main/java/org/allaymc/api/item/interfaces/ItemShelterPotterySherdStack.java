package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShelterPotterySherdStack extends ItemStack {
  ItemType<ItemShelterPotterySherdStack> SHELTER_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemShelterPotterySherdStack.class)
          .vanillaItem(VanillaItemId.SHELTER_POTTERY_SHERD)
          .build();
}
