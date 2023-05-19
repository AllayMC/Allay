package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.darkOakDoor extends ItemStack{
        ItemType<ItemItem.darkOakDoor>TYPE=ItemTypeBuilder
        .builder(ItemItem.darkOakDoor.class)
        .vanillaItem(VanillaItemId.DARK_OAK_DOOR,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
