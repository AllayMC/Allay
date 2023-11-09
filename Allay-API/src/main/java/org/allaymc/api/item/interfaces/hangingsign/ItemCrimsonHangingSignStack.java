package org.allaymc.api.item.interfaces.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonHangingSignStack extends ItemStack {
  ItemType<ItemCrimsonHangingSignStack> CRIMSON_HANGING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonHangingSignStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_HANGING_SIGN)
          .build();
}
