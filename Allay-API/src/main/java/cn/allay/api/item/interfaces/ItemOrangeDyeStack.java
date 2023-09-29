package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeDyeStack extends ItemStack {
  ItemType<ItemOrangeDyeStack> ORANGE_DYE_TYPE = ItemTypeBuilder
          .builder(ItemOrangeDyeStack.class)
          .vanillaItem(VanillaItemId.ORANGE_DYE)
          .build();
}
