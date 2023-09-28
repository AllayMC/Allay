package cn.allay.api.item.interfaces.trapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleTrapdoorStack extends ItemStack {
  ItemType<ItemJungleTrapdoorStack> JUNGLE_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemJungleTrapdoorStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_TRAPDOOR)
          .build();
}
