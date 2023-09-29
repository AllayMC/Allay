package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenPickaxeStack extends ItemStack {
  ItemType<ItemGoldenPickaxeStack> GOLDEN_PICKAXE_TYPE = ItemTypeBuilder
          .builder(ItemGoldenPickaxeStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_PICKAXE)
          .build();
}
