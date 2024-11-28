package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDetectorRailBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDetectorRailBehaviorImpl extends BlockBehaviorImpl implements BlockDetectorRailBehavior {
    public BlockDetectorRailBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
