package org.allaymc.server.block.impl.concretepowder;

import java.util.List;
import org.allaymc.api.block.interfaces.concretepowder.BlockCyanConcretePowderBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCyanConcretePowderBehaviorImpl extends BlockBehaviorImpl implements BlockCyanConcretePowderBehavior {
    public BlockCyanConcretePowderBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
