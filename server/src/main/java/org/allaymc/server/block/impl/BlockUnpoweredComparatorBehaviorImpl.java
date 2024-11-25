package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockUnpoweredComparatorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockUnpoweredComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockUnpoweredComparatorBehavior {
    public BlockUnpoweredComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
