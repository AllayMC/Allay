package cn.allay.api.item.interfaces.planks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooPlanksStack extends ItemStack {
  ItemType<ItemBambooPlanksStack> BAMBOO_PLANKS_TYPE = ItemTypeBuilder
          .builder(ItemBambooPlanksStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_PLANKS)
          .build();
}
