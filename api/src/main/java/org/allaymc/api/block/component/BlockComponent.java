package org.allaymc.api.block.component;

import org.allaymc.api.component.Component;

/**
 * {@code BlockComponent} marks components that contribute behavior to a block. Block
 * behavior interfaces combine these components into the capabilities exposed by a
 * block type, while server implementations supply the concrete lifecycle and
 * state-handling operations.
 *
 * @author daoge_cmd
 */
public interface BlockComponent extends Component {
}
