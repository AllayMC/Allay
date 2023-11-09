package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateLapisOreStack extends ItemStack {
  ItemType<ItemDeepslateLapisOreStack> DEEPSLATE_LAPIS_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateLapisOreStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_LAPIS_ORE)
          .build();
}
