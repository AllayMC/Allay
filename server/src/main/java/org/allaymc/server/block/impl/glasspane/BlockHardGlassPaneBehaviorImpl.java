package org.allaymc.server.block.impl.glasspane;

import java.util.List;
import org.allaymc.api.block.interfaces.glasspane.BlockHardGlassPaneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockHardGlassPaneBehaviorImpl extends BlockBehaviorImpl implements BlockHardGlassPaneBehavior {
    public BlockHardGlassPaneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
