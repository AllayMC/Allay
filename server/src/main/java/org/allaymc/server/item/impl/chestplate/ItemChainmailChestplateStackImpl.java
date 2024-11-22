package org.allaymc.server.item.impl.chestplate;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.chestplate.ItemChainmailChestplateStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemChainmailChestplateStackImpl extends ItemStackImpl implements ItemChainmailChestplateStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemChainmailChestplateStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
