package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public class BlockMagmaBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockMagmaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (entity instanceof EntityDamageComponent damageComponent) {
            if (entity instanceof EntityPlayer player && player.isSneaking()) {
                return;
            }

            damageComponent.attack(DamageContainer.magma(1));
        }
    }
}
