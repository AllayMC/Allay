package org.allaymc.api.item.interfaces.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkoakStandingSignStack extends ItemStack {
  ItemType<ItemDarkoakStandingSignStack> DARKOAK_STANDING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemDarkoakStandingSignStack.class)
          .vanillaItem(VanillaItemId.DARKOAK_STANDING_SIGN)
          .build();
}
