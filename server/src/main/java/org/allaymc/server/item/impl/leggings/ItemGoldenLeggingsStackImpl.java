package org.allaymc.server.item.impl.leggings;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.leggings.ItemGoldenLeggingsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemGoldenLeggingsStackImpl extends ItemStackImpl implements ItemGoldenLeggingsStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemGoldenLeggingsStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
