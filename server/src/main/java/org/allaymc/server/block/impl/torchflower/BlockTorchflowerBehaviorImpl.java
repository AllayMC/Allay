package org.allaymc.server.block.impl.torchflower;

import java.util.List;
import org.allaymc.api.block.interfaces.torchflower.BlockTorchflowerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockTorchflowerBehaviorImpl extends BlockBehaviorImpl implements BlockTorchflowerBehavior {
    public BlockTorchflowerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
