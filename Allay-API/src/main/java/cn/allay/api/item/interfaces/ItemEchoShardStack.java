package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEchoShardStack extends ItemStack {
  ItemType<ItemEchoShardStack> ECHO_SHARD_TYPE = ItemTypeBuilder
          .builder(ItemEchoShardStack.class)
          .vanillaItem(VanillaItemId.ECHO_SHARD)
          .build();
}
