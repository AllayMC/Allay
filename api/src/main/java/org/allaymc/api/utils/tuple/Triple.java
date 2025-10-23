package org.allaymc.api.utils.tuple;

/**
 * Triple is an immutable tuple of three values.
 *
 * @author daoge_cmd
 */
public record Triple<L, M, R>(L left, M middle, R right) {
}
