package org.allaymc.server.perm.tree;

/**
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
