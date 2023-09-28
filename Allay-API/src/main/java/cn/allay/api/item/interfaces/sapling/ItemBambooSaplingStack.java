package cn.allay.api.item.interfaces.sapling;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooSaplingStack extends ItemStack {
  ItemType<ItemBambooSaplingStack> BAMBOO_SAPLING_TYPE = ItemTypeBuilder
          .builder(ItemBambooSaplingStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_SAPLING)
          .build();
}
