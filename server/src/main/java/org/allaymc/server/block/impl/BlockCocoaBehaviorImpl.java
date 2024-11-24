package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockCocoaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCocoaBehaviorImpl extends BlockBehaviorImpl implements BlockCocoaBehavior {
    public BlockCocoaBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
