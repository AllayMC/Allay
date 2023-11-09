package org.allaymc.api.item.interfaces.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaStandingSignStack extends ItemStack {
  ItemType<ItemAcaciaStandingSignStack> ACACIA_STANDING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemAcaciaStandingSignStack.class)
          .vanillaItem(VanillaItemId.ACACIA_STANDING_SIGN)
          .build();
}
