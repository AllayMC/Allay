package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDriedKelpBlockStack extends ItemStack {
  ItemType<ItemDriedKelpBlockStack> DRIED_KELP_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemDriedKelpBlockStack.class)
          .vanillaItem(VanillaItemId.DRIED_KELP_BLOCK)
          .build();
}
