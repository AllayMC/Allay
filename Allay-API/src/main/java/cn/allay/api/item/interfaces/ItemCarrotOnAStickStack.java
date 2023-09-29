package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarrotOnAStickStack extends ItemStack {
  ItemType<ItemCarrotOnAStickStack> CARROT_ON_A_STICK_TYPE = ItemTypeBuilder
          .builder(ItemCarrotOnAStickStack.class)
          .vanillaItem(VanillaItemId.CARROT_ON_A_STICK)
          .build();
}
