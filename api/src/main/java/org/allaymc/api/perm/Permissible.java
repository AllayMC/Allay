package org.allaymc.api.perm;

import org.allaymc.api.perm.tree.PermTree;

import java.util.Collection;

/**
 * Represents a permissible object.
 *
 * @author daoge_cmd
 */
public interface Permissible {
    /**
     * Check if this object is an operator.
     *
     * @return {@code true} if this object is an operator, {@code false} otherwise.
     */
    default boolean isOp() {
        return getPermTree().isOp();
    }

    /**
     * Set if this object is an operator.
     *
     * @param value {@code true} if this object is an operator, {@code false} otherwise.
     */
    default void setOp(boolean value) {
        getPermTree().setOp(value);
    }

    /**
     * Check if this object has the given permission.
     *
     * @param perm the permission to check.
     * @return {@code true} if this object has the given permission, {@code false} otherwise.
     */
    default boolean hasPerm(String perm) {
        return getPermTree().hasPerm(perm);
    }

    /**
     * Check if this object has all the given permissions.
     *
     * @param perms the permissions to check.
     * @return {@code true} if this object has all the given permissions, {@code false} otherwise.
     */
    default boolean hasPerm(Collection<String> perms) {
        return perms.stream().allMatch(this::hasPerm);
    }

    /**
     * Add a permission to this object.
     *
     * @param perm the permission to add.
     * @return this object.
     */
    default Permissible addPerm(String perm) {
        getPermTree().addPerm(perm);
        return this;
    }

    /**
     * Remove a permission from this object.
     *
     * @param perm the permission to remove.
     * @return this object.
     */
    default Permissible removePerm(String perm) {
        getPermTree().removePerm(perm);
        return this;
    }

    /**
     * Set the value of a permission.
     *
     * @param perm the permission to set.
     * @param value {@code true} to add the permission, {@code false} to remove it.
     * @return this object.
     */
    default Permissible setPerm(String perm, boolean value) {
        if (value) addPerm(perm);
        else removePerm(perm);
        return this;
    }

    /**
     * Get the permission tree of this object.
     *
     * @return the permission tree of this object.
     */
    PermTree getPermTree();
}
