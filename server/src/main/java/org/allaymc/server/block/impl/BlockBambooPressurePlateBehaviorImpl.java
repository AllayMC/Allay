package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBambooPressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBambooPressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockBambooPressurePlateBehavior {
    public BlockBambooPressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
