package org.allaymc.api.item.data;

/**
 * {@code ItemTag} represents an item classification by its tag name.
 * Item types collect these values for capability and tier checks; record equality
 * compares the stored name exactly, without normalizing namespaces, case, or
 * whitespace during construction.
 *
 * @param name exact item classification name
 *
 * @author daoge_cmd
 */
public record ItemTag(String name) {
}
