package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenPickaxeStack extends ItemStack {
  ItemType<ItemWoodenPickaxeStack> WOODEN_PICKAXE_TYPE = ItemTypeBuilder
          .builder(ItemWoodenPickaxeStack.class)
          .vanillaItem(VanillaItemId.WOODEN_PICKAXE)
          .build();
}
