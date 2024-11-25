package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedDioriteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPolishedDioriteBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedDioriteBehavior {
    public BlockPolishedDioriteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
