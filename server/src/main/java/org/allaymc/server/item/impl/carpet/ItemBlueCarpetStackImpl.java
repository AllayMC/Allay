package org.allaymc.server.item.impl.carpet;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.carpet.ItemBlueCarpetStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemBlueCarpetStackImpl extends ItemStackImpl implements ItemBlueCarpetStack {
    public ItemBlueCarpetStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}