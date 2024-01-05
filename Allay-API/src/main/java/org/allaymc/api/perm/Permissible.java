package org.allaymc.api.perm;

import org.allaymc.api.perm.tree.PermTree;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface Permissible {
    boolean isOp();

    void setOp(boolean value);

    boolean hasPerm(String perm);

    Permissible addPerm(String perm);

    Permissible removePerm(String perm);
}
