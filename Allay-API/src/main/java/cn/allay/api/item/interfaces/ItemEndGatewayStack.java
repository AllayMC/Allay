package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndGatewayStack extends ItemStack {
  ItemType<ItemEndGatewayStack> END_GATEWAY_TYPE = ItemTypeBuilder
          .builder(ItemEndGatewayStack.class)
          .vanillaItem(VanillaItemId.END_GATEWAY)
          .build();
}
