package org.allaymc.server.block.impl.head;

import java.util.List;
import org.allaymc.api.block.interfaces.head.BlockPlayerHeadBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPlayerHeadBehaviorImpl extends BlockBehaviorImpl implements BlockPlayerHeadBehavior {
    public BlockPlayerHeadBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
