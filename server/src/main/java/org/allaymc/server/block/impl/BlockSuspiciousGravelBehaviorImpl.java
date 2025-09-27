package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSuspiciousGravelBehaviorImpl extends BlockBehaviorImpl implements BlockSuspiciousGravelBehavior {
    public BlockSuspiciousGravelBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
