package org.allaymc.api.item.interfaces.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooHangingSignStack extends ItemStack {
  ItemType<ItemBambooHangingSignStack> BAMBOO_HANGING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemBambooHangingSignStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_HANGING_SIGN)
          .build();
}
