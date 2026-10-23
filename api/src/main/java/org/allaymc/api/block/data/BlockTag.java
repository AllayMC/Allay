package org.allaymc.api.block.data;

/**
 * {@code BlockTag} represents a named category used to group block types for
 * behavior checks. Record equality compares the stored name, so independently
 * created tags with equal names can match the same entries in a block type's tag
 * set.
 *
 * @param name block category name
 *
 * @author daoge_cmd
 */
public record BlockTag(String name) {
}
