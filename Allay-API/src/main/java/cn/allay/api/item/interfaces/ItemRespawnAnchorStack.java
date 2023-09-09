package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRespawnAnchorStack extends ItemStack {
    ItemType<ItemRespawnAnchorStack> RESPAWN_ANCHOR_TYPE = ItemTypeBuilder
            .builder(ItemRespawnAnchorStack.class)
            .vanillaItem(VanillaItemId.RESPAWN_ANCHOR)
            .build();
}
