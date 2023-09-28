package cn.allay.api.item.interfaces.trapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakTrapdoorStack extends ItemStack {
  ItemType<ItemDarkOakTrapdoorStack> DARK_OAK_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakTrapdoorStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_TRAPDOOR)
          .build();
}
