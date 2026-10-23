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
import org.allaymc.server.entity.component.event.CEntityParallelTickEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;

/**
 * {@code EntityAIComponentImpl} connects an entity to the server
 * behavior-group implementation. Parallel tick events run AI only for alive,
 * mobile entities, while ordinary tick events drain the group's deferred
 * actions after the entity manager has joined the parallel phase.
 *
 * @author daoge_cmd
 */
public class EntityAIComponentImpl implements EntityAIComponent {

    /**
     * {@code IDENTIFIER} names this component implementation for provider
     * lookup and replacement when entity types assemble their component sets.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_ai_component");

    /**
     * Injected intelligent entity owning this AI component.
     */
    @ComponentObject
    protected EntityIntelligent thisEntity;

    /**
     * {@code behaviorGroup} supplies the active AI and shared memory.
     * Replacing it binds the new group to {@code thisEntity}; callers must
     * avoid replacement during either tick phase.
     */
    @Getter
    protected BehaviorGroupImpl behaviorGroup;

    /**
     * Creates an AI adapter retaining the supplied server behavior group. The
     * argument must be a {@code BehaviorGroupImpl}; entity binding occurs
     * later in {@code onInitFinish} after owner injection.
     *
     * @throws ClassCastException if {@code behaviorGroup} is another implementation
     */
    public EntityAIComponentImpl(BehaviorGroup behaviorGroup) {
        this.behaviorGroup = (BehaviorGroupImpl) behaviorGroup;
    }

    /**
     * Binds the retained behavior group to the injected entity after
     * initialization.
     */
    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        behaviorGroup.setEntity(thisEntity);
    }

    /**
     * Replaces and binds the server behavior group used by both tick phases.
     * Callers must supply a {@code BehaviorGroupImpl} and avoid changing
     * groups while AI or deferred actions are executing.
     */
    @Override
    public void setBehaviorGroup(BehaviorGroup behaviorGroup) {
        this.behaviorGroup = (BehaviorGroupImpl) behaviorGroup;
        this.behaviorGroup.setEntity(thisEntity);
    }

    /**
     * Runs behavior-group AI when the owning entity is alive and mobile. The
     * entity manager completes this parallel phase before the ordinary tick
     * drains queued actions, so this callback must preserve that phase
     * separation.
     */
    @EventHandler
    protected void onParallelTick(CEntityParallelTickEvent event) {
        if (!thisEntity.isImmobile() && thisEntity.isAlive()) {
            behaviorGroup.tick();
        }
    }

    /**
     * Drains the current behavior group's deferred actions during the ordinary
     * entity tick. The entity manager must have joined parallel callbacks
     * first because the group's action queue relies on that ordering rather
     * than independent synchronization.
     */
    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        behaviorGroup.processSyncedActions();
    }
}
