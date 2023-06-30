package cn.allay.api.item.type;

import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identified;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponentImpl;

import java.util.List;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemType<T extends ItemStack> extends Identified {
    List<ComponentProvider<? extends ItemComponentImpl>> getComponentProviders();

    T createItemStack(ItemStackInitInfo info);
}
