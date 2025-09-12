package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockTntBaseComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.component.EntityTntBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockTntBaseComponentImpl extends BlockBaseComponentImpl implements BlockTntBaseComponent {
    public BlockTntBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        super.onInteract(itemStack, dimension, interactInfo);

        var block = new Block(interactInfo.getClickedBlock().getBlockState(), new Position3i(interactInfo.clickedBlockPos(), dimension));

        if (itemStack.getItemType() == ItemTypes.FIRE_CHARGE) {
            itemStack.reduceCount(1);
            prime(block);
            return true;
        }

        if (itemStack.hasEnchantment(EnchantmentTypes.FIRE_ASPECT)) {
            itemStack.tryIncreaseDamage(1);
            prime(block);
            return true;
        }

        return false;
    }

    @Override
    public void prime(Block block, int fuse) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());

        var angle = ThreadLocalRandom.current().nextFloat() * Math.PI * 2;
        var motion = new Vector3d(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);
        var entity = EntityTypes.TNT.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(pos.x() + 0.5, pos.y(), pos.z() + 0.5)
                        .motion(motion)
                        .nbt(NbtMap.builder().putShort(EntityTntBaseComponentImpl.TAG_FUSE, (short) fuse).build())
                        .build()
        );
        dimension.getEntityManager().addEntity(entity);
    }
}
