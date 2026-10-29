package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSuspiciousGravelBehaviorImpl extends BlockBehaviorImpl implements BlockSuspiciousGravelBehavior {
    @Delegate
    private BlockFallableComponent fallableComponent;

    public BlockSuspiciousGravelBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
