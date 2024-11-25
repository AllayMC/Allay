package org.allaymc.server.block.impl.torchflower;

import org.allaymc.api.block.interfaces.torchflower.BlockTorchflowerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTorchflowerBehaviorImpl extends BlockBehaviorImpl implements BlockTorchflowerBehavior {
    public BlockTorchflowerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
