package org.allaymc.server.perm.tree;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public class AllayRootPermNode extends AllayPermNode {

    public AllayRootPermNode(String name) {
        super(name, null);
    }

    @Override
    public boolean isRoot() {
        return true;
    }
}
