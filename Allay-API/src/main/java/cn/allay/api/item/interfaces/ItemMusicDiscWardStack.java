package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscWardStack extends ItemStack {
    ItemType<ItemMusicDiscWardStack> MUSIC_DISC_WARD_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscWardStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_WARD)
            .build();
}
