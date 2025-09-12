package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockUnpoweredRepeaterBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockUnpoweredRepeaterBehaviorImpl extends BlockBehaviorImpl implements BlockUnpoweredRepeaterBehavior {
    public BlockUnpoweredRepeaterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
