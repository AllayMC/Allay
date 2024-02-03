package org.allaymc.api.perm;

import org.allaymc.api.perm.tree.PermTree;

import java.util.Collection;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface Permissible {
    default boolean isOp() {
        return getPermTree().isOp();
    }

    default void setOp(boolean value) {
        getPermTree().setOp(value);
    }

    default boolean hasPerm(String perm) {
        return getPermTree().hasPerm(perm);
    }

    default boolean hasPerm(Collection<String> perms) {
        return perms.stream().allMatch(this::hasPerm);
    }

    default Permissible addPerm(String perm) {
        getPermTree().addPerm(perm);
        return this;
    }

    default Permissible removePerm(String perm) {
        getPermTree().removePerm(perm);
        return this;
    }

    default Permissible setPerm(String perm, boolean value) {
        if (value) addPerm(perm);
        else removePerm(perm);
        return this;
    }

    PermTree getPermTree();
}
