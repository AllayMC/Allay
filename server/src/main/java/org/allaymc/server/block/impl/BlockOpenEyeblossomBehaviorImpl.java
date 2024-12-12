package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOpenEyeblossomBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockOpenEyeblossomBehaviorImpl extends BlockBehaviorImpl implements BlockOpenEyeblossomBehavior {
    public BlockOpenEyeblossomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
