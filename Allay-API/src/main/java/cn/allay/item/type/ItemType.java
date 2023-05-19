package cn.allay.item.type;

import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeRegistry;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.identifier.Identified;
import cn.allay.item.ItemStack;
import cn.allay.item.component.ItemComponentImpl;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemType<T extends ItemStack> extends Identified {
    List<ComponentProvider<? extends ItemComponentImpl>> getComponentProviders();

    T createItemStack(ItemStackInitInfo info);

    default ItemType<T> register(ItemTypeRegistry registry) {
        registry.register(getNamespaceId(), this);
        return this;
    }
}
