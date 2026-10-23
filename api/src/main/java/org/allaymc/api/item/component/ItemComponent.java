package org.allaymc.api.item.component;

import org.allaymc.api.component.Component;

/**
 * {@code ItemComponent} marks a component that contributes behavior or state to an
 * item instance. Item type builders collect providers of these components, and
 * the component system combines their interfaces into the item stack exposed to
 * gameplay callers.
 *
 * @author daoge_cmd
 */
public interface ItemComponent extends Component {
}
