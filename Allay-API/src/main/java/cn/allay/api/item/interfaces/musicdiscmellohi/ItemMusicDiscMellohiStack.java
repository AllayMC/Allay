package cn.allay.api.item.interfaces.musicdiscmellohi;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscMellohiStack extends ItemStack {
    ItemType<ItemMusicDiscMellohiStack> MUSIC_DISC_MELLOHI_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscMellohiStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_MELLOHI)
            .build();
}
