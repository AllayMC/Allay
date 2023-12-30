package org.allaymc.api.perm.tree;

import org.allaymc.api.ApiInstanceHolder;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface PermTree {

    ApiInstanceHolder<PermTreeFactory> FACTORY = ApiInstanceHolder.of();

    static PermTree create() {
        return FACTORY.get().create();
    }

    PermNode getRoot();

    boolean hasPerm(String perm);

    void addPerm(String perm);

    void removePerm(String perm);

    interface PermTreeFactory {
        PermTree create();
    }
}
