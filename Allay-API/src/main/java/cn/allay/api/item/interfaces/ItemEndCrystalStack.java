package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndCrystalStack extends ItemStack {
  ItemType<ItemEndCrystalStack> END_CRYSTAL_TYPE = ItemTypeBuilder
          .builder(ItemEndCrystalStack.class)
          .vanillaItem(VanillaItemId.END_CRYSTAL)
          .build();
}
