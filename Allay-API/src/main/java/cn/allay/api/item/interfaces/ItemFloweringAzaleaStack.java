package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFloweringAzaleaStack extends ItemStack {
  ItemType<ItemFloweringAzaleaStack> FLOWERING_AZALEA_TYPE = ItemTypeBuilder
          .builder(ItemFloweringAzaleaStack.class)
          .vanillaItem(VanillaItemId.FLOWERING_AZALEA)
          .build();
}
