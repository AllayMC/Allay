package cn.allay.api.item.interfaces.rawcopperblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawCopperBlockStack extends ItemStack {
  ItemType<ItemRawCopperBlockStack> RAW_COPPER_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemRawCopperBlockStack.class)
          .vanillaItem(VanillaItemId.RAW_COPPER_BLOCK)
          .build();
}
