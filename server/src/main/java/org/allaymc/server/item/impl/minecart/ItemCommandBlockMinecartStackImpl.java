package org.allaymc.server.item.impl.minecart;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.minecart.ItemCommandBlockMinecartStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemCommandBlockMinecartStackImpl extends ItemStackImpl implements ItemCommandBlockMinecartStack {
    public ItemCommandBlockMinecartStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
