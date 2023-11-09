package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfestedDeepslateStack extends ItemStack {
  ItemType<ItemInfestedDeepslateStack> INFESTED_DEEPSLATE_TYPE = ItemTypeBuilder
          .builder(ItemInfestedDeepslateStack.class)
          .vanillaItem(VanillaItemId.INFESTED_DEEPSLATE)
          .build();
}
