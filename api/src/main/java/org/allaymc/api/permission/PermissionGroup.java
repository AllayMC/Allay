package org.allaymc.api.permission;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * PermissionGroup represents a set of permissions that can be assigned to a {@link Permissible} entity.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class PermissionGroup {

    private static final String TAG_PERMISSIONS = "Permissions";
    private static final String TAG_EXCLUDED_PERMISSIONS = "ExcludedPermissions";
    private static final String TAG_PARENTS = "Parents";

    private final String name;
    /**
     * If a permission is in the `permissions` set, its status is `true` <br>
     * If a permission is in the `excludedPermissions` set, its status is `false` <br>
     * If a permission is neither in the `permissions` set nor in the `excludedPermissions` set, its status is `default` (decided by the parents) <br>
     */
    private final Set<Permission> permissions, excludedPermissions;
    private final Set<PermissionGroup> parents;

    private PermissionGroup(String name, Set<Permission> permissions, Set<PermissionGroup> parents) {
        this.name = name;
        // Make sure the permissions set is mutable and independent
        this.permissions = new HashSet<>(permissions);
        this.excludedPermissions = new HashSet<>();
        this.parents = new HashSet<>(parents);
    }

    /**
     * @see #create(String, Set, Set, boolean)
     */
    public static PermissionGroup create(String name) {
        return create(name, Collections.emptySet());
    }

    /**
     * @see #create(String, Set, Set, boolean)
     */
    public static PermissionGroup create(String name, Set<Permission> permissions) {
        return create(name, permissions, Collections.emptySet());
    }

    /**
     * @see #create(String, Set, Set, boolean)
     */
    public static PermissionGroup create(String name, Set<Permission> permissions, Set<PermissionGroup> parents) {
        return create(name, permissions, parents, true);
    }

    /**
     * Creates a new permission group with the given name, permissions, and parents. The created permission group
     * will also be registered to {@link Registries#PERMISSION_GROUPS}.
     *
     * @param name        the name of the permission group
     * @param permissions the permissions associated with the permission group
     * @param parents     the parent permission groups of this permission group
     * @return a new permission group instance
     * @throws PermissionException if the name already exists
     */
    public static PermissionGroup create(String name, Set<Permission> permissions, Set<PermissionGroup> parents, boolean register) {
        var group = new PermissionGroup(name, permissions, parents);

        if (register) {
            if (Registries.PERMISSION_GROUPS.get(name) != null) {
                throw new PermissionException("Permission group with name " + name + " already exists");
            }
            Registries.PERMISSION_GROUPS.register(name, group);
        }
        return group;
    }

    /**
     * Gets an existing permission group by its name.
     *
     * @param name the name of the permission group
     * @return the permission group with the given name, or {@code null} if it does not exist
     */
    public static PermissionGroup get(String name) {
        return Registries.PERMISSION_GROUPS.get(name);
    }

    /**
     * Gets the name of this permission group.
     *
     * @return the name of this permission group
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the permissions associated with this permission group.
     *
     * @return a set of permissions associated with this permission group
     */
    @UnmodifiableView
    public Set<Permission> getPermissions() {
        return getPermissions(true);
    }

    /**
     * Retrieves an unmodifiable view of the set of permissions that are excluded.
     *
     * @return an unmodifiable {@code Set} of {@code Permission} objects representing the excluded permissions.
     */
    @UnmodifiableView
    public Set<Permission> getExcludedPermissions() {
        return Collections.unmodifiableSet(excludedPermissions);
    }

    /**
     * Gets the permissions associated with this permission group.
     *
     * @param includeParentPermissions whether to include permissions from the parent permission group
     * @return a set of permissions associated with this permission group
     */
    @UnmodifiableView
    public Set<Permission> getPermissions(boolean includeParentPermissions) {
        if (!includeParentPermissions || parents.isEmpty()) {
            return Collections.unmodifiableSet(permissions);
        }

        var set = new HashSet<>(permissions);
        for (var parent : parents) {
            var parentPermissions = new HashSet<>(parent.getPermissions());
            // Parent's permissions may have been excluded in the current permission group, remove excluded permissions.
            parentPermissions.removeAll(excludedPermissions);
            set.addAll(parentPermissions);
        }

        return set;
    }

    /**
     * Gets the parents' permissions associated with this permission group.
     *
     * @return a set of parents' permissions associated with this permission group
     */
    @UnmodifiableView
    public Set<Permission> getParentPermissions() {
        var set = new HashSet<Permission>();
        for (var parent : parents) {
            set.addAll(parent.getPermissions());
        }
        return set;
    }

    /**
     * Gets the parent permission groups of this permission group.
     *
     * @return the parent permission groups
     */
    @UnmodifiableView
    public Set<PermissionGroup> getParents() {
        return Collections.unmodifiableSet(parents);
    }

    /**
     * Checks if this permission group has a specific parent.
     *
     * @param parent the parent to check
     * @return {@code true} if this permission group have the specific parent, otherwise {@code false}
     */
    public boolean hasParent(PermissionGroup parent) {
        return this.parents.contains(parent);
    }

    /**
     * Checks if this permission group has the given permission.
     *
     * @param permission the permission to check
     * @return {@code true} if this permission group has the permission, {@code false} otherwise.
     */
    public boolean hasPermission(Permission permission) {
        // Check if this permission group has the permission directly
        if (permissions.contains(permission)) {
            return true;
        }

        // Check if this permission is excluded from this permission group
        if (excludedPermissions.contains(permission)) {
            return false;
        }

        // Check the parent's permissions
        for (var parent : parents) {
            if (parent.hasPermission(permission)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if this permission group has all the given permissions.
     *
     * @param permissions the permissions to check
     * @return {@code true} if this permission group has all the permissions, {@code false} otherwise.
     */
    public boolean hasPermissions(Permission... permissions) {
        for (Permission permission : permissions) {
            if (!hasPermission(permission)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if this permission group has all the given permissions.
     *
     * @param permissions the permissions to check
     * @return {@code true} if this permission group has all the permissions, {@code false} otherwise.
     */
    public boolean hasPermissions(Collection<Permission> permissions) {
        for (Permission permission : permissions) {
            if (!hasPermission(permission)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if this permission group has all permissions that are existing in the given permission group.
     *
     * @param group                    the permission group to check
     * @param includeParentPermissions whether to include permissions from the parent permission group
     * @return {@code true} if this permission group has all permissions that are existing in the given permission group, {@code false} otherwise.
     */
    public boolean hasPermissions(PermissionGroup group, boolean includeParentPermissions) {
        return hasPermissions(group.getPermissions(includeParentPermissions));
    }

    /**
     * @see #addPermission(Permission, Permissible)
     */
    public PermissionGroup addPermission(Permission permission) {
        return addPermission(permission, null);
    }

    /**
     * Adds a permission to this permission group.
     *
     * @param permission  the permission to add
     * @param permissible the permissible entity that this permission group belongs to
     *                    Can be {@code null} if the permission does not belong to any entity.
     * @return this permission group instance
     */
    public PermissionGroup addPermission(Permission permission, Permissible permissible) {
        return setPermission(permission, true, permissible);
    }

    /**
     * @see #removePermission(Permission, Permissible)
     */
    public PermissionGroup removePermission(Permission permission) {
        return removePermission(permission, null);
    }

    /**
     * Removes a permission from this permission group.
     *
     * @param permission  the permission to remove
     * @param permissible the permissible entity that this permission group belongs to
     *                    Can be {@code null} if the permission does not belong to any entity.
     * @return this permission group instance
     */
    public PermissionGroup removePermission(Permission permission, Permissible permissible) {
        return setPermission(permission, false, permissible);
    }

    /**
     * @see #setPermission(Permission, boolean, Permissible)
     */
    public PermissionGroup setPermission(Permission permission, boolean value) {
        return setPermission(permission, value, null);
    }

    /**
     * Sets a permission for this permission group.
     *
     * @param permission  the permission to set
     * @param value       {@code true} to add the permission, {@code false} to remove it
     * @param permissible the permissible entity that this permission group belongs to
     *                    Can be {@code null} if the permission does not belong to any entity.
     * @return this permission group instance
     */
    public PermissionGroup setPermission(Permission permission, boolean value, Permissible permissible) {
        if (hasPermission(permission) == value) {
            return this;
        }

        if (value) {
            excludedPermissions.remove(permission);
            if (!hasPermission(permission)) {
                permissions.add(permission);
            }
        } else {
            permissions.remove(permission);
            if (hasPermission(permission)) {
                excludedPermissions.add(permission);
            }
        }

        permission.getListeners().forEach(listener -> listener.onChange(permissible, value));
        return this;
    }

    /**
     * Resets a permission for this permission group. This would set the permission status to `default`, which means
     * that the parents decide the value for this permission (the value may change or may not change).
     *
     * @param permission  the permission to reset
     * @param permissible the permissible entity that this permission group belongs to.
     *                    Can be {@code null} if the permission does not belong to any entity.
     * @return this permission group instance
     */
    public PermissionGroup resetPermission(Permission permission, Permissible permissible) {
        var oldValue = hasPermission(permission);
        permissions.remove(permission);
        excludedPermissions.remove(permission);
        var newValue = hasPermission(permission);

        if (oldValue != newValue) {
            permission.getListeners().forEach(listener -> listener.onChange(permissible, newValue));
        }

        return this;
    }

    /**
     * @see #addParent(PermissionGroup, Permissible)
     */
    public PermissionGroup addParent(PermissionGroup parent) {
        return addParent(parent, null);
    }

    /**
     * Adds a parent permission group to this permission group.
     *
     * @param parent      the parent permission group to add
     * @param permissible the permissible entity that this permission group belongs to. Can be {@code null} if the
     *                    permission does not belong to any entity.
     * @return this permission group instance
     */
    public PermissionGroup addParent(PermissionGroup parent, Permissible permissible) {
        if (this.parents.contains(parent)) {
            return this;
        }

        var oldPermissions = getPermissions();
        this.parents.add(parent);
        var newPermissions = getPermissions();

        var addedPermissions = Sets.difference(newPermissions, oldPermissions);
        for (var permission : addedPermissions) {
            permission.getListeners().forEach(listener -> listener.onChange(permissible, true));
        }

        return this;
    }

    /**
     * @see #removeParent(PermissionGroup, Permissible)
     */
    public PermissionGroup removeParent(PermissionGroup parent) {
        return removeParent(parent, null);
    }

    /**
     * Removes a parent permission group from this permission group.
     *
     * @param parent      the parent permission group to remove
     * @param permissible the permissible entity that this permission group belongs to. Can be {@code null} if the
     *                    permission does not belong to any entity.
     * @return this permission group instance
     */
    public PermissionGroup removeParent(PermissionGroup parent, Permissible permissible) {
        if (!this.parents.contains(parent)) {
            return this;
        }

        var oldPermissions = getPermissions();
        this.parents.remove(parent);
        var newPermissions = getPermissions();

        var removedPermissions = Sets.difference(oldPermissions, newPermissions);
        for (var permission : removedPermissions) {
            permission.getListeners().forEach(listener -> listener.onChange(permissible, false));
        }

        return this;
    }

    /**
     * Checks if this permission group has all permissions that are existing in {@link PermissionGroups#OPERATOR}.
     *
     * @return {@code true} if this permission group has all permissions that are existing in {@link PermissionGroups#OPERATOR}, {@code false} otherwise.
     */
    public boolean isOperator() {
        return hasPermissions(PermissionGroups.OPERATOR, true);
    }

    /**
     * @see #setOperator(boolean, Permissible)
     */
    public PermissionGroup setOperator(boolean value) {
        return setOperator(value, null);
    }

    /**
     * Sets the operator status of this permission group.
     * <p>
     * This method call may not be effective since the current permission group already has all the permissions exist in {@link PermissionGroups#OPERATOR}.
     * When a permission group has all the permissions that are existing in {@link PermissionGroups#OPERATOR}, we consider its operator status as {@code true}.
     *
     * @param value       {@code true} to set this permission group as operator, {@code false} to remove the operator status
     * @param permissible the permissible entity that this permission group belongs to. Can be {@code null} if the permission does not belong to any entity
     * @return this permission group instance
     */
    public PermissionGroup setOperator(boolean value, Permissible permissible) {
        if (value) {
            addParent(PermissionGroups.OPERATOR, permissible);
            removeParent(PermissionGroups.MEMBER, permissible);
        } else {
            // Add MEMBER to the parents first to avoid unnecessary permission changes since
            addParent(PermissionGroups.MEMBER, permissible);
            removeParent(PermissionGroups.OPERATOR, permissible);
        }

        return this;
    }

    /**
     * Saves this permission group to an NBT map.
     *
     * @return an NBT map representing this permission group
     */
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();

        builder.putList(TAG_PERMISSIONS, NbtType.STRING, getPermissions(false).stream().map(Permission::getName).toList());
        builder.putList(TAG_EXCLUDED_PERMISSIONS, NbtType.STRING, getExcludedPermissions().stream().map(Permission::getName).toList());
        if (!parents.isEmpty()) {
            builder.putList(TAG_PARENTS, NbtType.STRING, parents.stream().map(PermissionGroup::getName).toList());
        }

        return builder.build();
    }

    /**
     * @see #loadNBT(NbtMap, Permissible)
     */
    public PermissionGroup loadNBT(NbtMap nbt) {
        return loadNBT(nbt, null);
    }

    /**
     * Loads permission group info from an NBT map.
     *
     * @param nbt         the NBT map to load from
     * @param permissible the permissible entity that this permission group belongs to. Can be {@code null} if the permission does not belong to any entity
     */
    public PermissionGroup loadNBT(NbtMap nbt, Permissible permissible) {
        nbt.getList(TAG_PERMISSIONS, NbtType.STRING).forEach(name -> {
            var permission = Permission.get(name);
            if (permission == null) {
                log.warn("Find unknown permission '{}' when loading permission group '{}'", name, this.name);
                return;
            }

            addPermission(permission, permissible);
        });
        nbt.getList(TAG_EXCLUDED_PERMISSIONS, NbtType.STRING).forEach(name -> {
            var permission = Permission.get(name);
            if (permission == null) {
                log.warn("Find unknown permission '{}' when loading permission group '{}'", name, this.name);
                return;
            }

            removePermission(permission, permissible);
        });
        nbt.listenForList(TAG_PARENTS, NbtType.STRING, names -> {
            for (var name : names) {
                var parent = PermissionGroup.get(name);
                if (parent == null) {
                    log.warn("Find unknown parent permission group '{}' when loading permission group '{}'. Fallback to the default permission group", name, this.name);
                    parent = PermissionGroups.DEFAULT.get();
                }

                addParent(parent, permissible);
            }
        });
        return this;
    }
}
