package org.allaymc.server.block.impl.element;

import java.util.List;
import org.allaymc.api.block.interfaces.element.BlockElement59Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockElement59BehaviorImpl extends BlockBehaviorImpl implements BlockElement59Behavior {
    public BlockElement59BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
