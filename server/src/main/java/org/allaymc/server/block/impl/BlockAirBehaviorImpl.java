package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAirBehaviorImpl extends BlockBehaviorImpl implements BlockAirBehavior {
    public BlockAirBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
