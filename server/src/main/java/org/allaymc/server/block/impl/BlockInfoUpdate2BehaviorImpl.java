package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockInfoUpdate2Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockInfoUpdate2BehaviorImpl extends BlockBehaviorImpl implements BlockInfoUpdate2Behavior {
    public BlockInfoUpdate2BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
