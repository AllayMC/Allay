package org.allaymc.server.item.impl.coralblock;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.coralblock.ItemDeadBrainCoralBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemDeadBrainCoralBlockStackImpl extends ItemStackImpl implements ItemDeadBrainCoralBlockStack {
    public ItemDeadBrainCoralBlockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
