package org.allaymc.api.perm;

import org.allaymc.api.perm.tree.PermTree;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface Permissible {
    boolean isOp();

    boolean setOp(boolean value);

    PermTree getPermTree();

    default boolean hasPerm(String perm) {
        return getPermTree().hasPerm(perm);
    }

    default Permissible addPerm(String perm) {
        getPermTree().addPerm(perm);
        return this;
    }

    default Permissible removePerm(String perm) {
        getPermTree().removePerm(perm);
        return this;
    }
}
