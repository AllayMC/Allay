package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.server.Server;

/**
 * Sets the entity in love mode for a specified duration and sends
 * love particles to viewers periodically.
 *
 * @author daoge_cmd
 */
public class InLoveExecutor implements BehaviorExecutor {

    protected final int duration;
    protected int tickCounter;

    public InLoveExecutor(int duration) {
        this.duration = duration;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        tickCounter = 0;
        entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, true);
        entity.getMemoryStorage().put(MemoryTypes.LAST_IN_LOVE_TIME, Server.getInstance().getTick());
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;
        if (tickCounter > duration) {
            return false;
        }

        // Send love particles every 10 ticks
        if (tickCounter % 10 == 0) {
            entity.applyAction(SimpleEntityAction.IN_LOVE);
        }

        return true;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        entity.getMemoryStorage().put(MemoryTypes.IS_IN_LOVE, false);
    }
}
