package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChorusFlowerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockChorusFlowerBehaviorImpl extends BlockBehaviorImpl implements BlockChorusFlowerBehavior {
    public BlockChorusFlowerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
