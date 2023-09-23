package cn.allay.api.item.interfaces.npcspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNpcSpawnEggStack extends ItemStack {
    ItemType<ItemNpcSpawnEggStack> NPC_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemNpcSpawnEggStack.class)
            .vanillaItem(VanillaItemId.NPC_SPAWN_EGG)
            .build();
}
