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
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface PermTree {

    ApiInstanceHolder<PermTreeFactory> FACTORY = ApiInstanceHolder.create();

    static PermTree create() {
        return FACTORY.get().create();
    }

    static PermTree create(String name) {
        return FACTORY.get().create(name);
    }

    String getName();

    PermTree registerPermListener(String perm, Consumer<PermChangeType> callback);

    @UnmodifiableView
    default Map<String, Consumer<PermChangeType>> getPermListeners() {
        return getPermListeners(false);
    }

    @UnmodifiableView
    Map<String, Consumer<PermChangeType>> getPermListeners(boolean includeParent);

    void notifyAllPermListeners();

    void clear();

    boolean containsSubSet(PermTree other);

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

    default List<PermNode> getLeaves() {
        return getLeaves(false);
    }

    List<PermNode> getLeaves(boolean includeParent);

    boolean isOp();

    PermTree setOp(boolean op);

    PermTree getParent();

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

    default void loadNBT(NbtMap nbt) {
        loadNBT(nbt, true);
    }

    default void loadNBT(NbtMap nbt, boolean callListener) {
        nbt.getList("Perms", NbtType.STRING).forEach(perm -> addPerm(perm, callListener));
        copyFrom(DefaultPermissions.byName(nbt.getString("PermLevel")));
    }

    enum PermChangeType {
        ADD,
        REMOVE
    }

    interface PermTreeFactory {
        default PermTree create() {
            return create("");
        }

        PermTree create(String name);
    }
}
