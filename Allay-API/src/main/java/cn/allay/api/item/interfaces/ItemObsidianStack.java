package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemObsidianStack extends ItemStack {
  ItemType<ItemObsidianStack> OBSIDIAN_TYPE = ItemTypeBuilder
          .builder(ItemObsidianStack.class)
          .vanillaItem(VanillaItemId.OBSIDIAN)
          .build();
}
