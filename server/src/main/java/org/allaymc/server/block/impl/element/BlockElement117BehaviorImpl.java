package org.allaymc.server.block.impl.element;

import java.util.List;
import org.allaymc.api.block.interfaces.element.BlockElement117Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockElement117BehaviorImpl extends BlockBehaviorImpl implements BlockElement117Behavior {
    public BlockElement117BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
