package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityHeadYawComponent;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EntityHeadYawComponentImpl} stores head yaw independently of the
 * entity's body rotation. The generated getter and setter expose the angle in
 * degrees, and entity movement encoding reads this value for entities that
 * provide the head-yaw component.
 *
 * @author daoge_cmd
 */
public class EntityHeadYawComponentImpl implements EntityHeadYawComponent {

    /**
     * {@code IDENTIFIER} names this component implementation for provider
     * lookup and replacement when entity types assemble their component sets.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_head_yaw_component");

    /**
     * Independent head yaw measured in degrees.
     */
    @Getter
    @Setter
    protected double headYaw;
}
