package org.allaymc.api.item.interfaces.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceStandingSignStack extends ItemStack {
  ItemType<ItemSpruceStandingSignStack> SPRUCE_STANDING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemSpruceStandingSignStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_STANDING_SIGN)
          .build();
}
