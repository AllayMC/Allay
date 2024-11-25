package org.allaymc.server.block.impl.sand;

import org.allaymc.api.block.interfaces.sand.BlockSuspiciousSandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSuspiciousSandBehaviorImpl extends BlockBehaviorImpl implements BlockSuspiciousSandBehavior {
    public BlockSuspiciousSandBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
