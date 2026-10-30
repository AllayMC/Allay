package org.allaymc.api.utils.tuple;

/**
 * {@code Pair} holds two values in named {@code left} and {@code right}
 * positions. Its canonical constructor retains both references directly and
 * permits {@code null} values; the record cannot replace its components, but
 * mutable objects stored in either position remain shared with their original
 * owners.
 *
 * @param left left component reference
 * @param right right component reference
 *
 * @author daoge_cmd
 */
public record Pair<L, R>(L left, R right) {
}
