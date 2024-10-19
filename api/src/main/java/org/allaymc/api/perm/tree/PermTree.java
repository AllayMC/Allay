package org.allaymc.api.perm.tree;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.perm.DefaultPermissions;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.allaymc.api.perm.DefaultPermissions.*;

/**
 * Represents a permission tree.
 *
 * @author daoge_cmd
 */
public interface PermTree {

    ApiInstanceHolder<Factory> FACTORY = ApiInstanceHolder.create();

    /**
     * Creates a new permission tree.
     *
     * @return a new permission tree.
     */
    static PermTree create() {
        return FACTORY.get().create();
    }

    /**
     * Creates a new permission tree with the given name.
     *
     * @param name the name of the permission tree.
     *
     * @return a new permission tree.
     */
    static PermTree create(String name) {
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
     * @param perm     the permission to listen for.
     * @param callback the callback to invoke when the permission changes.
     *
     * @return this permission tree.
     */
    PermTree registerPermListener(String perm, Consumer<PermChangeType> callback);

    /**
     * Gets all permission listeners of this permission tree.
     *
     * @return a map of all permission listeners.
     */
    @UnmodifiableView
    default Map<String, Consumer<PermChangeType>> getPermListeners() {
        return getPermListeners(false);
    }

    /**
     * Gets all permission listeners of this permission tree.
     *
     * @param includeParent whether to include the parent's listeners.
     *
     * @return a map of all permission listeners.
     */
    @UnmodifiableView
    Map<String, Consumer<PermChangeType>> getPermListeners(boolean includeParent);

    /**
     * Notifies all permission listeners of this permission tree.
     */
    void notifyAllPermListeners();

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
    boolean containsSubSet(PermTree other);

    /**
     * Gets the root node of this permission tree.
     *
     * @return the root node of this permission tree.
     */
    PermNode getRoot();

    /**
     * Add a permission to this permission tree.
     *
     * @param perm         the permission to add.
     * @param callListener whether to call the permission listener.
     *
     * @return this permission tree.
     */
    PermTree addPerm(String perm, boolean callListener);

    /**
     * Add a permission to this permission tree.
     *
     * @param perm the permission to add.
     *
     * @return this permission tree.
     */
    default PermTree addPerm(String perm) {
        return addPerm(perm, true);
    }

    /**
     * Set the value of a permission.
     *
     * @param perm  the permission to set.
     * @param value the value to set.
     *
     * @return this permission tree.
     */
    default PermTree setPerm(String perm, boolean value) {
        if (value) addPerm(perm);
        else removePerm(perm);
        return this;
    }

    /**
     * Checks if this permission tree has the given permission.
     *
     * @param perm the permission to check.
     *
     * @return {@code true} if this permission tree has the given permission, {@code false} otherwise.
     */
    boolean hasPerm(String perm);

    /**
     * Remove a permission from this permission tree.
     *
     * @param perm         the permission to remove.
     * @param callListener whether to call the permission listener.
     *
     * @return this permission tree.
     */
    PermTree removePerm(String perm, boolean callListener);

    /**
     * Remove a permission from this permission tree.
     *
     * @param perm the permission to remove.
     *
     * @return this permission tree.
     */
    default PermTree removePerm(String perm) {
        return removePerm(perm, true);
    }

    /**
     * Extend this permission tree from the given parent.
     *
     * @param parent the parent to extend from.
     *
     * @return this permission tree.
     */
    default PermTree extendFrom(PermTree parent) {
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
    PermTree extendFrom(PermTree parent, boolean callListener);

    /**
     * Copy permissions from the given parent.
     *
     * @param parent the parent to copy from.
     *
     * @return this permission tree.
     */
    PermTree copyFrom(PermTree parent);

    /**
     * Gets all leaves of this permission tree.
     *
     * @return a list of all leaves.
     */
    default List<PermNode> getLeaves() {
        return getLeaves(false);
    }

    /**
     * Gets all leaves of this permission tree.
     *
     * @param includeParent whether to include the parent's leaves.
     *
     * @return a list of all leaves.
     */
    List<PermNode> getLeaves(boolean includeParent);

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
    PermTree setOp(boolean op);

    /**
     * Gets the parent of this permission tree.
     *
     * @return the parent of this permission tree.
     */
    PermTree getParent();

    /**
     * Saves this permission tree to NBT.
     *
     * @return the NBT representation of this permission tree.
     */
    default NbtMap saveNBT() {
        var builder = NbtMap.builder();
        var list = getLeaves().stream().map(PermNode::getFullName).toList();
        builder.putList("Perms", NbtType.STRING, list);

        if (getParent() != null)
            builder.putString("Parent", getParent().getName());

        builder.put(
                "PermLevel",
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
        nbt.getList("Perms", NbtType.STRING).forEach(perm -> addPerm(perm, callListener));
        copyFrom(DefaultPermissions.byName(nbt.getString("PermLevel")));
    }

    /**
     * Represents a permission change type.
     */
    enum PermChangeType {
        ADD,
        REMOVE
    }

    interface Factory {
        default PermTree create() {
            return create("");
        }

        PermTree create(String name);
    }
}
