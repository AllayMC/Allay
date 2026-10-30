package org.allaymc.api.utils.identifier;

/**
 * {@code Identified} exposes the resource identifier associated with an
 * implementing object. This common contract lets registry and domain code
 * obtain the identifier without depending on a concrete type; the
 * implementation supplies the namespace and path represented by that
 * identifier.
 *
 * @author daoge_cmd
 */
public interface Identified {
    /**
     * Returns the resource identifier associated with this implementing object.
     */
    Identifier getIdentifier();
}
