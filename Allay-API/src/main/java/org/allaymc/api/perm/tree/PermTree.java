package org.allaymc.api.perm.tree;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.perm.DefaultPermissions;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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

    static PermTree create(String name) {
        return FACTORY.get().create(name);
    }

    String getName();

    PermTree registerPermListener(String perm, Consumer<PermChangeType> callback);

    @UnmodifiableView
    Map<String, Consumer<PermChangeType>> getPermListeners();

    void notifyAllPermListeners();

    void clear();

    boolean containsSubSet(PermTree other);

    enum PermChangeType {
        ADD,
        REMOVE
    }

    PermNode getRoot();

    PermTree addPerm(String perm, boolean callListener);

    default PermTree addPerm(String perm) {
        return addPerm(perm, true);
    }

    default PermTree setPerm(String perm, boolean value) {
        if (value) addPerm(perm);
        else removePerm(perm);
        return this;
    }

    boolean hasPerm(String perm);

    PermTree removePerm(String perm, boolean callListener);

    default PermTree removePerm(String perm) {
        return removePerm(perm, true);
    }

    default PermTree extendFrom(PermTree parent) {
        return extendFrom(parent, true);
    }

    PermTree extendFrom(PermTree parent, boolean callListener);

    PermTree copyFrom(PermTree parent);

    List<PermNode> getLeaves();

    boolean isOp();

    PermTree setOp(boolean op);

    PermTree getParent();

    default NbtMap saveNBT() {
        var builder = NbtMap.builder();
        var list = new ArrayList<String>();
        for (var leaf : getLeaves()) {
            list.add(leaf.getFullName());
        }
        builder.putList("Perms", NbtType.STRING, list);
        if (getParent() != null)
            builder.putString("Parent", getParent().getName());
        return builder.build();
    }

    default void loadNBT(NbtMap nbt) {
        loadNBT(nbt, true);
    }

    default void loadNBT(NbtMap nbt, boolean callListener) {
        for (var perm : nbt.getList("Perms", NbtType.STRING))
            addPerm(perm, callListener);
        if (nbt.containsKey("Parent"))
            extendFrom(DefaultPermissions.byName(nbt.getString("Parent")), callListener);
    }

    interface PermTreeFactory {
        default PermTree create() {
            return create("");
        }

        PermTree create(String name);
    }
}
