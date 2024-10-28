package org.allaymc.api.permission.tree;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.permission.DefaultPermissions;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.allaymc.api.permission.DefaultPermissions.*;

/**
 * Represents a permission tree.
 *
 * @author daoge_cmd
 */
public interface PermissionTree {

    ApiInstanceHolder<Factory> FACTORY = ApiInstanceHolder.create();

    /**
     * Creates a new permission tree.
     *
     * @return a new permission tree.
     */
    static PermissionTree create() {
        return FACTORY.get().create();
    }

    /**
     * Creates a new permission tree with the given name.
     *
     * @param name the name of the permission tree.
     *
     * @return a new permission tree.
     */
    static PermissionTree create(String name) {
        return FACTORY.get().create(name);
    }

    /**
     * Gets the name of this permission tree.
     *
     * @return the name of this permission tree.
     */
    String getName();

    /**
     * Registers a permission listener for the given permission.
     *
     * @param permission the permission to listen for.
     * @param callback the callback to invoke when the permission changes.
     *
     * @return this permission tree.
     */
    PermissionTree registerPermissionListener(String permission, Consumer<PermissionChangeType> callback);

    /**
     * Gets all permission listeners of this permission tree.
     *
     * @return a map of all permission listeners.
     */
    @UnmodifiableView
    default Map<String, Consumer<PermissionChangeType>> getPermissionListeners() {
        return getPermissionListeners(false);
    }

    /**
     * Gets all permission listeners of this permission tree.
     *
     * @param includeParent whether to include the parent's listeners.
     *
     * @return a map of all permission listeners.
     */
    @UnmodifiableView
    Map<String, Consumer<PermissionChangeType>> getPermissionListeners(boolean includeParent);

    /**
     * Notifies all permission listeners of this permission tree.
     */
    void notifyAllPermissionListeners();

    /**
     * Clears all permissions of this permission tree.
     */
    void clear();

    /**
     * Checks if this permission tree contains the given subset.
     *
     * @param other the subset to check.
     *
     * @return {@code true} if this permission tree contains the given subset, {@code false} otherwise.
     */
    boolean containsSubSet(PermissionTree other);

    /**
     * Gets the root node of this permission tree.
     *
     * @return the root node of this permission tree.
     */
    PermissionNode getRoot();

    /**
     * Add a permission to this permission tree.
     *
     * @param permission         the permission to add.
     * @param callListener whether to call the permission listener.
     *
     * @return this permission tree.
     */
    PermissionTree addPermission(String permission, boolean callListener);

    /**
     * Add a permission to this permission tree.
     *
     * @param permission the permission to add.
     *
     * @return this permission tree.
     */
    default PermissionTree addPermission(String permission) {
        return addPermission(permission, true);
    }

    /**
     * Set the value of a permission.
     *
     * @param permission the permission to set.
     * @param value the value to set.
     *
     * @return this permission tree.
     */
    default PermissionTree setPermission(String permission, boolean value) {
        if (value) addPermission(permission);
        else removePermission(permission);
        return this;
    }

    /**
     * Checks if this permission tree has the given permission.
     *
     * @param permission the permission to check.
     *
     * @return {@code true} if this permission tree has the given permission, {@code false} otherwise.
     */
    boolean hasPermission(String permission);

    /**
     * Remove a permission from this permission tree.
     *
     * @param permission the permission to remove.
     * @param callListener whether to call the permission listener.
     *
     * @return this permission tree.
     */
    PermissionTree removePermission(String permission, boolean callListener);

    /**
     * Remove a permission from this permission tree.
     *
     * @param permission the permission to remove.
     *
     * @return this permission tree.
     */
    default PermissionTree removePermission(String permission) {
        return removePermission(permission, true);
    }

    /**
     * Extend this permission tree from the given parent.
     *
     * @param parent the parent to extend from.
     *
     * @return this permission tree.
     */
    default PermissionTree extendFrom(PermissionTree parent) {
        return extendFrom(parent, true);
    }

    /**
     * Extend this permission tree from the given parent.
     *
     * @param parent       the parent to extend from.
     * @param callListener whether to call the permission listener.
     *
     * @return this permission tree.
     */
    PermissionTree extendFrom(PermissionTree parent, boolean callListener);

    /**
     * Copy permissions from the given parent.
     *
     * @param parent the parent to copy from.
     *
     * @return this permission tree.
     */
    PermissionTree copyFrom(PermissionTree parent);

    /**
     * Gets all leaves of this permission tree.
     *
     * @return a list of all leaves.
     */
    default List<PermissionNode> getLeaves() {
        return getLeaves(false);
    }

    /**
     * Gets all leaves of this permission tree.
     *
     * @param includeParent whether to include the parent's leaves.
     *
     * @return a list of all leaves.
     */
    List<PermissionNode> getLeaves(boolean includeParent);

    /**
     * Checks if this permission tree is an operator.
     *
     * @return {@code true} if this permission tree is an operator, {@code false} otherwise.
     */
    boolean isOp();

    /**
     * Sets the operator status of this permission tree.
     *
     * @param op the operator status to set.
     *
     * @return this permission tree.
     */
    PermissionTree setOp(boolean op);

    /**
     * Gets the parent of this permission tree.
     *
     * @return the parent of this permission tree.
     */
    PermissionTree getParent();

    /**
     * Saves this permission tree to NBT.
     *
     * @return the NBT representation of this permission tree.
     */
    default NbtMap saveNBT() {
        var builder = NbtMap.builder();
        var list = getLeaves().stream().map(PermissionNode::getFullName).toList();
        builder.putList("Permissions", NbtType.STRING, list);

        if (getParent() != null)
            builder.putString("Parent", getParent().getName());

        builder.put(
                "PermissionLevel",
                containsSubSet(OPERATOR) ? OPERATOR.getName() :
                        containsSubSet(MEMBER) ? MEMBER.getName() :
                                VISITOR.getName()
        );
        return builder.build();
    }

    /**
     * Loads this permission tree from NBT.
     *
     * @param nbt the NBT to load from.
     */
    default void loadNBT(NbtMap nbt) {
        loadNBT(nbt, true);
    }

    /**
     * Loads this permission tree from NBT.
     *
     * @param nbt          the NBT to load from.
     * @param callListener whether to call the permission listener.
     */
    default void loadNBT(NbtMap nbt, boolean callListener) {
        nbt.getList("Permissions", NbtType.STRING).forEach(permission -> addPermission(permission, callListener));
        copyFrom(DefaultPermissions.byName(nbt.getString("PermissionLevel")));
    }

    /**
     * Represents a permission change type.
     */
    enum PermissionChangeType {
        ADD,
        REMOVE
    }

    interface Factory {
        default PermissionTree create() {
            return create("");
        }

        PermissionTree create(String name);
    }
}
