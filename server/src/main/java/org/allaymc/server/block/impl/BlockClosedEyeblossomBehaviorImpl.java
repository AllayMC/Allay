package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockClosedEyeblossomBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockClosedEyeblossomBehaviorImpl extends BlockBehaviorImpl implements BlockClosedEyeblossomBehavior {
    public BlockClosedEyeblossomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
