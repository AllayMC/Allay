package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.ai.behaviorgroup.BehaviorGroup;
import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.ai.behaviorgroup.BehaviorGroupImpl;
import org.allaymc.server.entity.component.event.CEntityTickEvent;

/**
 * @author daoge_cmd
 */
public class EntityAIComponentImpl implements EntityAIComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_ai_component");

    @ComponentObject
    protected EntityIntelligent thisEntity;

    @Getter
    protected BehaviorGroup behaviorGroup;

    public EntityAIComponentImpl(BehaviorGroup behaviorGroup) {
        this.behaviorGroup = behaviorGroup;
    }

    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        ((BehaviorGroupImpl) behaviorGroup).setEntity(thisEntity);
    }

    @Override
    public void setBehaviorGroup(BehaviorGroup behaviorGroup) {
        this.behaviorGroup = behaviorGroup;
        ((BehaviorGroupImpl) behaviorGroup).setEntity(thisEntity);
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (thisEntity.isImmobile()) return;

        ((BehaviorGroupImpl) behaviorGroup).tick();
    }
}
