package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLadderStack extends ItemStack {
  ItemType<ItemLadderStack> LADDER_TYPE = ItemTypeBuilder
          .builder(ItemLadderStack.class)
          .vanillaItem(VanillaItemId.LADDER)
          .build();
}
