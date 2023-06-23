package cn.allay.api.item.type;

import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identified;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponentImpl;

import java.util.List;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemType<T extends ItemStack> extends Identified {
    List<ComponentProvider<? extends ItemComponentImpl>> getComponentProviders();

    T createItemStack(ItemStackInitInfo info);

    default ItemType<T> register(ItemTypeRegistry registry) {
        registry.register(getIdentifier(), this);
        return this;
    }
}
