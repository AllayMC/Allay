package org.allaymc.server.block.type;

import org.allaymc.api.component.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code TestBlockImpl} supplies the fixture behavior class used by custom block
 * builders.
 *
 * @author daoge_cmd
 */
public class TestBlockImpl extends BlockBehaviorImpl implements TestBlock {
    /**
     * Creates fixture block behavior by forwarding component providers to the
     * shared implementation.
     */
    public TestBlockImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
