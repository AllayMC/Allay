package cn.allay.api.item.interfaces.stainedglasspane;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanStainedGlassPaneStack extends ItemStack {
  ItemType<ItemCyanStainedGlassPaneStack> CYAN_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemCyanStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.CYAN_STAINED_GLASS_PANE)
          .build();
}
