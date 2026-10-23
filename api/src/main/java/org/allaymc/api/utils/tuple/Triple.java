package org.allaymc.api.utils.tuple;

/**
 * {@code Triple} holds three values in named {@code left}, {@code middle}, and
 * {@code right} positions. Its canonical constructor retains all references
 * directly and permits {@code null} values; the record cannot replace its
 * components, but mutable objects stored in any position remain shared with
 * their original owners.
 *
 * @param left left component reference
 * @param middle middle component reference
 * @param right right component reference
 *
 * @author daoge_cmd
 */
public record Triple<L, M, R>(L left, M middle, R right) {
}
