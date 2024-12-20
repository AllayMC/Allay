package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.api.eventbus.event.entity.EntityDamageEvent;

/**
 * @author daoge_cmd
 */
public class BlockLavaBaseComponentImpl extends BlockLiquidBaseComponentImpl {
    public BlockLavaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onCollideWithEntity(BlockStateWithPos blockStateWithPos, Entity entity) {
        if (!(entity instanceof EntityDamageComponent damageComponent)) {
            return;
        }

        // Set on fire ticks
        var event1 = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, blockStateWithPos.blockState(), 20 * 15);
        if (event1.call()) {
            damageComponent.setOnFireTicks(event1.getOnFireTicks());
        }

        // Lava damage
        if (damageComponent.hasFireDamage() && entity.getWorld().getTick() % 10 == 0) {
            var event2 = new EntityDamageEvent(entity, DamageContainer.lava(4));
            if (event2.call()) {
                damageComponent.attack(event2.getDamageContainer(), true);
            }
        }
    }
}
