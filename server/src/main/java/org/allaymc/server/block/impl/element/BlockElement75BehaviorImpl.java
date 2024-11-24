package org.allaymc.server.block.impl.element;

import java.util.List;
import org.allaymc.api.block.interfaces.element.BlockElement75Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockElement75BehaviorImpl extends BlockBehaviorImpl implements BlockElement75Behavior {
    public BlockElement75BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
