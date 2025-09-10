package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;

/**
 * @author daoge_cmd
 */
public class EntityAgeComponentImpl implements EntityAgeComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_age_component");
    public static final int DEFAULT_MAX_AGE = 6000;

    protected static final String TAG_AGE = "Age";
    @Getter
    protected final int maxAge;
    @ComponentObject
    protected Entity thisEntity;
    @Getter
    @Setter
    protected int age;

    public EntityAgeComponentImpl() {
        this(DEFAULT_MAX_AGE);
    }

    public EntityAgeComponentImpl(int maxAge) {
        this.maxAge = maxAge;
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (age != -1) {
            age++;
            if (age >= maxAge) {
                thisEntity.remove();
            }
        }
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForShort(TAG_AGE, age -> this.age = age);
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().put(TAG_AGE, this.age);
    }
}
