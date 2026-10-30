package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EntityProjectileComponentImpl} retains optional shooter attribution
 * for a projectile. Generated accessors expose and replace the same entity
 * reference, while collision and impact consumers decide separately whether
 * the shooter is still alive or in the applicable dimension.
 *
 * @author daoge_cmd
 */
public class EntityProjectileComponentImpl implements EntityProjectileComponent {

    /**
     * {@code IDENTIFIER} selects this shooter-reference component during
     * component registration and dependency lookup by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_projectile_component");

    /**
     * {@code shooter} retains the assigned launch source, or {@code null} when
     * absent. The component neither copies the entity nor manages its
     * lifetime.
     */
    @Getter
    @Setter
    protected Entity shooter;
}
