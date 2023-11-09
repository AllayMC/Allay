package org.allaymc.api.item.interfaces.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayWoolStack extends ItemStack {
  ItemType<ItemLightGrayWoolStack> LIGHT_GRAY_WOOL_TYPE = ItemTypeBuilder
          .builder(ItemLightGrayWoolStack.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_WOOL)
          .build();
}
