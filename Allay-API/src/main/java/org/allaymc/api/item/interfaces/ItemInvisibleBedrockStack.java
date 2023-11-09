package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInvisibleBedrockStack extends ItemStack {
  ItemType<ItemInvisibleBedrockStack> INVISIBLE_BEDROCK_TYPE = ItemTypeBuilder
          .builder(ItemInvisibleBedrockStack.class)
          .vanillaItem(VanillaItemId.INVISIBLE_BEDROCK)
          .build();
}
