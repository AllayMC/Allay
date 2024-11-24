package org.allaymc.server.block.impl.sponge;

import java.util.List;
import org.allaymc.api.block.interfaces.sponge.BlockSpongeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSpongeBehaviorImpl extends BlockBehaviorImpl implements BlockSpongeBehavior {
    public BlockSpongeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
