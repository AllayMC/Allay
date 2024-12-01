package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDaylightDetectorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDaylightDetectorBehaviorImpl extends BlockBehaviorImpl implements BlockDaylightDetectorBehavior {
    public BlockDaylightDetectorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
