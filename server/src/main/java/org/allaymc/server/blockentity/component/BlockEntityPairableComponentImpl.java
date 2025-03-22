package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author IWareQ
 */
public class BlockEntityPairableComponentImpl implements BlockEntityPairableComponent {
    protected static final String TAG_ALLAY_PAIR_LEAD = "allay:pairlead";
    protected static final String TAG_PAIR_X = "pairx";
    protected static final String TAG_PAIR_Z = "pairz";

    @Dependency
    protected BlockEntityBaseComponent baseComponent;

    @Getter
    protected boolean lead;
    protected int pairX;
    protected int pairZ;

    @Override
    public boolean tryPairWith(BlockEntity other) {
        if (isPaired()) {
            return false;
        }

        pairX = other.getPosition().x();
        pairZ = other.getPosition().z();

        baseComponent.sendBlockEntityDataPacketToViewers();

        var pair = (BlockEntityPairableComponent) getPair();
        if (pair.isPaired()) {
            lead = true;
        }

        return true;
    }

    @Override
    public void unpair() {
        lead = false;

        pairX = Integer.MIN_VALUE;
        pairZ = Integer.MIN_VALUE;

        baseComponent.sendBlockEntityDataPacketToViewers();
    }

    @EventHandler
    protected void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForBoolean(TAG_ALLAY_PAIR_LEAD, value -> lead = value);
        nbt.listenForInt(TAG_PAIR_X, value -> pairX = value);
        nbt.listenForInt(TAG_PAIR_Z, value -> pairZ = value);
    }

    @EventHandler
    protected void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        if (isPaired()) {
            builder.putBoolean(TAG_ALLAY_PAIR_LEAD, lead)
                    .putInt(TAG_PAIR_X, pairX)
                    .putInt(TAG_PAIR_Z, pairZ);
        }
    }

    @EventHandler
    protected void onReplace(CBlockOnReplaceEvent event) {
        var blockEntity = event.getCurrentBlockState().getBlockEntity();
        if (blockEntity instanceof BlockEntityPairableComponent pairableComponent) {
            if (pairableComponent.isPaired()) {
                ((BlockEntityPairableComponent) pairableComponent.getPair()).unpair();
                pairableComponent.unpair();
            }
        }
    }

    @Override
    public BlockEntity getPair() {
        if (pairX == Integer.MIN_VALUE && pairZ == Integer.MIN_VALUE) {
            return null;
        }

        return baseComponent.getDimension().getBlockEntity(pairX, baseComponent.getPosition().y(), pairZ);
    }
}
