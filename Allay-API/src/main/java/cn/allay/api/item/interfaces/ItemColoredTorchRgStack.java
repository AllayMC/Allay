package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemColoredTorchRgStack extends ItemStack {
  ItemType<ItemColoredTorchRgStack> COLORED_TORCH_RG_TYPE = ItemTypeBuilder
          .builder(ItemColoredTorchRgStack.class)
          .vanillaItem(VanillaItemId.COLORED_TORCH_RG)
          .build();
}
