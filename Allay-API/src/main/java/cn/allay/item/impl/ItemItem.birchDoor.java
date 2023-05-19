package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.birchDoor extends ItemStack{
        ItemType<ItemItem.birchDoor>TYPE=ItemTypeBuilder
        .builder(ItemItem.birchDoor.class)
        .vanillaItem(VanillaItemId.BIRCH_DOOR,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
