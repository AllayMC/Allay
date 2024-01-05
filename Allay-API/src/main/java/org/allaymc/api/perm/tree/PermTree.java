package org.allaymc.api.perm.tree;

import org.allaymc.api.ApiInstanceHolder;

import java.util.List;

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

    PermTree addPerm(String perm);

    boolean hasPerm(String perm);

    PermTree removePerm(String perm);

    PermTree extendFrom(PermTree parent);

    List<PermNode> getLeaves();

    interface PermTreeFactory {
        PermTree create();
    }
}
