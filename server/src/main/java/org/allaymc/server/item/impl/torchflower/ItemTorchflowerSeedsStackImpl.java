package org.allaymc.server.item.impl.torchflower;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.torchflower.ItemTorchflowerSeedsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemTorchflowerSeedsStackImpl extends ItemStackImpl implements ItemTorchflowerSeedsStack {
    public ItemTorchflowerSeedsStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
