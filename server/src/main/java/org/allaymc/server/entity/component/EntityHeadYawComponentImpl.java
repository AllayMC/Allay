package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityHeadYawComponent;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EntityHeadYawComponentImpl implements EntityHeadYawComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_head_yaw_component");

    @Getter
    @Setter
    protected double headYaw;
}
