package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockUnpoweredComparatorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockUnpoweredComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockUnpoweredComparatorBehavior {
    public BlockUnpoweredComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
