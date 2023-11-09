package org.allaymc.api.item.interfaces.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueWoolStack extends ItemStack {
  ItemType<ItemLightBlueWoolStack> LIGHT_BLUE_WOOL_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueWoolStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_WOOL)
          .build();
}
