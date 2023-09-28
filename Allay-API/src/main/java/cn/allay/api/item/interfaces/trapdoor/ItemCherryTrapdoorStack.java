package cn.allay.api.item.interfaces.trapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryTrapdoorStack extends ItemStack {
  ItemType<ItemCherryTrapdoorStack> CHERRY_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemCherryTrapdoorStack.class)
          .vanillaItem(VanillaItemId.CHERRY_TRAPDOOR)
          .build();
}
