package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCryingObsidianStack extends ItemStack {
  ItemType<ItemCryingObsidianStack> CRYING_OBSIDIAN_TYPE = ItemTypeBuilder
          .builder(ItemCryingObsidianStack.class)
          .vanillaItem(VanillaItemId.CRYING_OBSIDIAN)
          .build();
}
