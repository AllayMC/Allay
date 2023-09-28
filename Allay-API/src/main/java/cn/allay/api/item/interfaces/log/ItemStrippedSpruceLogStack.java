package cn.allay.api.item.interfaces.log;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedSpruceLogStack extends ItemStack {
  ItemType<ItemStrippedSpruceLogStack> STRIPPED_SPRUCE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemStrippedSpruceLogStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_SPRUCE_LOG)
          .build();
}
