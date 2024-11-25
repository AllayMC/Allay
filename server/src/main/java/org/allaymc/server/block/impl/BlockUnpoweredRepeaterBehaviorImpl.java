package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockUnpoweredRepeaterBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockUnpoweredRepeaterBehaviorImpl extends BlockBehaviorImpl implements BlockUnpoweredRepeaterBehavior {
    public BlockUnpoweredRepeaterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
