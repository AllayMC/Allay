package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMonsterEggStack extends ItemStack {
  ItemType<ItemMonsterEggStack> MONSTER_EGG_TYPE = ItemTypeBuilder
          .builder(ItemMonsterEggStack.class)
          .vanillaItem(VanillaItemId.MONSTER_EGG)
          .build();
}
