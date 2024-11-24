package org.allaymc.server.block.impl.head;

import java.util.List;
import org.allaymc.api.block.interfaces.head.BlockCreeperHeadBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCreeperHeadBehaviorImpl extends BlockBehaviorImpl implements BlockCreeperHeadBehavior {
    public BlockCreeperHeadBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
