package org.allaymc.server.entity.component;

import org.allaymc.api.container.BaseContainerHolder;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentedObject;
import org.allaymc.server.entity.component.event.CEntityDieEvent;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntityContainerHolderComponentImpl extends BaseContainerHolder implements EntityContainerHolderComponent {

    @Identifier.Component
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_holder_component");

    @ComponentedObject
    protected static Entity thisEntity;

    public EntityContainerHolderComponentImpl(Container... containers) {
        super(containers);
    }

    @Override
    public @UnmodifiableView Map<FullContainerType<?>, Container> getContainers() {
        return super.getContainers();
    }

    @Override
    public <T extends Container> T getContainer(FullContainerType<T> type) {
        return super.getContainer(type);
    }

    @Override
    public void addContainer(Container container) {
        super.addContainer(container);
    }

    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        var pos = thisEntity.getLocation();
        var dimension = pos.dimension();
        var rand = ThreadLocalRandom.current();
        for (var container : getContainers().values()) {
            container.removeAllViewers();
            if (!canDropItemInContainers()) continue;
            for (var itemStack : container.getItemStacks()) {
                if (itemStack == ItemAirStack.AIR_STACK) continue;
                dimension.dropItem(
                        itemStack,
                        new Vector3f(
                                pos.x() + rand.nextFloat(0.5f) + 0.25f,
                                pos.y() + rand.nextFloat(0.5f) + 0.25f,
                                pos.z() + rand.nextFloat(0.5f) + 0.25f
                        ),
                        new Vector3f(
                                rand.nextFloat(1f) - 0.5f,
                                0.5f,
                                rand.nextFloat(1f) - 0.5f
                        ),
                        40
                );
            }

            container.clearAllSlots();
        }
    }

    protected boolean canDropItemInContainers() {
        return thisEntity.getWorld().getWorldData().getGameRule(GameRule.DO_ENTITY_DROPS);
    }
}
