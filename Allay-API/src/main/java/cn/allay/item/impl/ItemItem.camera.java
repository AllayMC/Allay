package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.camera extends ItemStack{
        ItemType<ItemItem.camera>TYPE=ItemTypeBuilder
        .builder(ItemItem.camera.class)
        .vanillaItem(VanillaItemId.CAMERA,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
