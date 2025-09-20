package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDetectorRailBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDetectorRailBehaviorImpl extends BlockBehaviorImpl implements BlockDetectorRailBehavior {
    public BlockDetectorRailBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
