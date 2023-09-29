package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonePickaxeStack extends ItemStack {
  ItemType<ItemStonePickaxeStack> STONE_PICKAXE_TYPE = ItemTypeBuilder
          .builder(ItemStonePickaxeStack.class)
          .vanillaItem(VanillaItemId.STONE_PICKAXE)
          .build();
}
