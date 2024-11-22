package org.allaymc.server.item.impl.helmet;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.helmet.ItemNetheriteHelmetStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemNetheriteHelmetStackImpl extends ItemStackImpl implements ItemNetheriteHelmetStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemNetheriteHelmetStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
