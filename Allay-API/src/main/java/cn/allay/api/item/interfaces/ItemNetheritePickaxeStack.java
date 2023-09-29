package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheritePickaxeStack extends ItemStack {
  ItemType<ItemNetheritePickaxeStack> NETHERITE_PICKAXE_TYPE = ItemTypeBuilder
          .builder(ItemNetheritePickaxeStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_PICKAXE)
          .build();
}
