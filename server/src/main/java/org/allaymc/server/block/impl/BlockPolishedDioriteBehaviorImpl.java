package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedDioriteBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPolishedDioriteBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedDioriteBehavior {
    public BlockPolishedDioriteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
