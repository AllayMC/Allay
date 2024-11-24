package org.allaymc.server.block.impl.sponge;

import java.util.List;
import org.allaymc.api.block.interfaces.sponge.BlockWetSpongeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWetSpongeBehaviorImpl extends BlockBehaviorImpl implements BlockWetSpongeBehavior {
    public BlockWetSpongeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
