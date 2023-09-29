package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCommandBlockStack extends ItemStack {
  ItemType<ItemCommandBlockStack> COMMAND_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemCommandBlockStack.class)
          .vanillaItem(VanillaItemId.COMMAND_BLOCK)
          .build();
}
