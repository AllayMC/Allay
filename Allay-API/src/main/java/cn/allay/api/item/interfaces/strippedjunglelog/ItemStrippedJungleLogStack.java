package cn.allay.api.item.interfaces.strippedjunglelog;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedJungleLogStack extends ItemStack {
    ItemType<ItemStrippedJungleLogStack> STRIPPED_JUNGLE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedJungleLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_JUNGLE_LOG)
            .build();
}
