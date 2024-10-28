package org.allaymc.server.permission.tree;

/**
 * @author daoge_cmd
 */
public class AllayRootPermissionNode extends AllayPermissionNode {

    public AllayRootPermissionNode(String name) {
        super(name, null);
    }

    @Override
    public boolean isRoot() {
        return true;
    }
}
