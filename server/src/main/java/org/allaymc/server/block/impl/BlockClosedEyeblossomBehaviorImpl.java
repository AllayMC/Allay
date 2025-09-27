package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockClosedEyeblossomBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockClosedEyeblossomBehaviorImpl extends BlockBehaviorImpl implements BlockClosedEyeblossomBehavior {
    public BlockClosedEyeblossomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
