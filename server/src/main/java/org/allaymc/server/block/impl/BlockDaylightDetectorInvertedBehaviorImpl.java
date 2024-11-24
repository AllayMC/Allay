package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockDaylightDetectorInvertedBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDaylightDetectorInvertedBehaviorImpl extends BlockBehaviorImpl implements BlockDaylightDetectorInvertedBehavior {
    public BlockDaylightDetectorInvertedBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
