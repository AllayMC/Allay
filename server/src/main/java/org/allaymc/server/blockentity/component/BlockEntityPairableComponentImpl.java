package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveCleanNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author IWareQ
 */
public class BlockEntityPairableComponentImpl implements BlockEntityPairableComponent {
    protected static final String TAG_PAIR_LEAD = "allay:pairlead";
    protected static final String TAG_PAIR_X = "pairx";
    protected static final String TAG_PAIR_Z = "pairz";

    @Dependency
    protected BlockEntityBaseComponentImpl baseComponent;

    @Getter
    protected boolean lead;
    protected Integer pairX;
    protected Integer pairZ;

    @Override
    public boolean tryPairWith(BlockEntity other) {
        if (isPaired()) {
            return false;
        }

        pairX = other.getPosition().x();
        pairZ = other.getPosition().z();

        baseComponent.sendBlockEntityToViewers();

        var pair = (BlockEntityPairableComponent) getPair();
        if (!pair.isPaired()) {
            lead = true;
        }

        return true;
    }

    @Override
    public void unpair() {
        lead = false;

        pairX = null;
        pairZ = null;

        baseComponent.sendBlockEntityToViewers();
    }

    @EventHandler
    protected void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForBoolean(TAG_PAIR_LEAD, value -> lead = value);
        nbt.listenForInt(TAG_PAIR_X, value -> pairX = value);
        nbt.listenForInt(TAG_PAIR_Z, value -> pairZ = value);
    }

    @EventHandler
    protected void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        if (isPaired()) {
            builder.putBoolean(TAG_PAIR_LEAD, lead)
                    .putInt(TAG_PAIR_X, pairX)
                    .putInt(TAG_PAIR_Z, pairZ);
        }
    }

    @EventHandler
    protected void onSaveCleanNBT(CBlockEntitySaveCleanNBTEvent event) {
        var builder = event.getNbt();
        builder.remove(TAG_PAIR_LEAD);
        builder.remove(TAG_PAIR_X);
        builder.remove(TAG_PAIR_Z);
    }

    @EventHandler
    protected void onReplace(CBlockOnReplaceEvent event) {
        var blockEntity = event.getCurrentBlock().getBlockEntity();
        if (blockEntity instanceof BlockEntityPairableComponent pairableComponent) {
            if (pairableComponent.isPaired()) {
                ((BlockEntityPairableComponent) pairableComponent.getPair()).unpair();
                pairableComponent.unpair();
            }
        }
    }

    @Override
    public BlockEntity getPair() {
        return baseComponent.getDimension().getBlockEntity(pairX, baseComponent.getPosition().y(), pairZ);
    }

    @Override
    public boolean isPaired() {
        return pairX != null && pairZ != null;
    }
}
