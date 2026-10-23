package org.allaymc.api.item.component;

/**
 * {@code ItemToolComponent} marks an item stack as a tool for component-based
 * item handling. The marker adds no operations of its own; tool implementations
 * attach interaction and durability behavior separately, and protocol metadata
 * uses the marker to identify tool items.
 *
 * @author daoge_cmd
 */
public interface ItemToolComponent extends ItemComponent {
}
