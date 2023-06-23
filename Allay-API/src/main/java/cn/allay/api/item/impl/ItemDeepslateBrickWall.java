package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBrickWall extends ItemStack {
  ItemType<ItemDeepslateBrickWall> TYPE = ItemTypeBuilder
          .builder(ItemDeepslateBrickWall.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_WALL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
