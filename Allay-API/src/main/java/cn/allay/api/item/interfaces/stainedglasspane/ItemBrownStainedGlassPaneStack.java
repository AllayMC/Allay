package cn.allay.api.item.interfaces.stainedglasspane;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownStainedGlassPaneStack extends ItemStack {
  ItemType<ItemBrownStainedGlassPaneStack> BROWN_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemBrownStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.BROWN_STAINED_GLASS_PANE)
          .build();
}
