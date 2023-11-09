package org.allaymc.api.item.interfaces.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleStandingSignStack extends ItemStack {
  ItemType<ItemJungleStandingSignStack> JUNGLE_STANDING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemJungleStandingSignStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_STANDING_SIGN)
          .build();
}
