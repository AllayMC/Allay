package cn.allay.api.item.interfaces.leaves;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaLeavesStack extends ItemStack {
  ItemType<ItemAzaleaLeavesStack> AZALEA_LEAVES_TYPE = ItemTypeBuilder
          .builder(ItemAzaleaLeavesStack.class)
          .vanillaItem(VanillaItemId.AZALEA_LEAVES)
          .build();
}
