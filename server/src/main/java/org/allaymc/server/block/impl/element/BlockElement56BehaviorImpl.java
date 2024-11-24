package org.allaymc.server.block.impl.element;

import java.util.List;
import org.allaymc.api.block.interfaces.element.BlockElement56Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockElement56BehaviorImpl extends BlockBehaviorImpl implements BlockElement56Behavior {
    public BlockElement56BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
