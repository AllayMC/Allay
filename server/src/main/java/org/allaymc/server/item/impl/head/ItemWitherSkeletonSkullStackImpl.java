package org.allaymc.server.item.impl.head;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.head.ItemWitherSkeletonSkullStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWitherSkeletonSkullStackImpl extends ItemStackImpl implements ItemWitherSkeletonSkullStack {
    public ItemWitherSkeletonSkullStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
