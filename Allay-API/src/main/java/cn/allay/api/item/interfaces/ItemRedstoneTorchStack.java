package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneTorchStack extends ItemStack {
  ItemType<ItemRedstoneTorchStack> REDSTONE_TORCH_TYPE = ItemTypeBuilder
          .builder(ItemRedstoneTorchStack.class)
          .vanillaItem(VanillaItemId.REDSTONE_TORCH)
          .build();
}
