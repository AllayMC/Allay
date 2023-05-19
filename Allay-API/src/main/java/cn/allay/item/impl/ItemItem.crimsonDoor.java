package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.crimsonDoor extends ItemStack{
        ItemType<ItemItem.crimsonDoor>TYPE=ItemTypeBuilder
        .builder(ItemItem.crimsonDoor.class)
        .vanillaItem(VanillaItemId.CRIMSON_DOOR,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
