package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.component.BlockEntityItemFrameBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnPunchEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.jetbrains.annotations.Range;

/**
 * @author daoge_cmd
 */
public class BlockEntityItemFrameBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityItemFrameBaseComponent {

    protected static final String TAG_ITEM = "Item";
    protected static final String TAG_ITEM_ROTATION = "ItemRotation";
    protected static final String TAG_ITEM_DROP_CHANCE = "ItemDropChance";

    protected ItemStack itemStack;
    protected @Range(from = 0, to = 7) byte itemRotation;

    public BlockEntityItemFrameBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.itemStack = ItemAirStack.AIR_STACK;
    }

    @Override
    public void onInteract(CBlockOnInteractEvent event) {
        super.onInteract(event);

        if (itemStack != ItemAirStack.AIR_STACK) {
            // Rotate the item in the frame
            setItemRotation((byte) ((itemRotation + 1) % 8));
            event.getDimension().addLevelEvent(MathUtils.center(event.getInteractInfo().clickedBlockPos()), LevelEvent.SOUND_ITEMFRAME_ITEM_ROTATE);
        } else {
            // Move the item from player's hand to the frame
            var itemStack = event.getItemStack().copy();
            itemStack.setCount(1);
            setItemStack(itemStack);
            event.getInteractInfo().player().tryConsumeItemInHand();
            event.getDimension().addLevelEvent(MathUtils.center(event.getInteractInfo().clickedBlockPos()), LevelEvent.SOUND_ITEMFRAME_ITEM_ADD);
        }

        event.setSuccess(true);
    }

    @Override
    public void onPunch(CBlockOnPunchEvent event) {
        super.onPunch(event);

        if (itemStack == ItemAirStack.AIR_STACK) {
            return;
        }

        var dimension = event.getCurrentBlockState().getDimension();
        if (!(event.getEntity() instanceof EntityPlayer player) || player.getGameType() != GameType.CREATIVE) {
            // Item won't drop if the entity is a dropItem in creative mode
            dimension.dropItem(itemStack, MathUtils.center(event.getCurrentBlockState().getPos()));
        }
        clearItemStack();
        dimension.addLevelEvent(MathUtils.center(event.getCurrentBlockState().getPos()), LevelEvent.SOUND_ITEMFRAME_ITEM_REMOVE);
    }

    @Override
    public void onReplace(CBlockOnReplaceEvent event) {
        super.onReplace(event);

        if (this.itemStack != ItemAirStack.AIR_STACK) {
            // Drop the item in the frame when the frame is replaced
            event.getCurrentBlockState().getDimension().dropItem(itemStack, MathUtils.center(event.getCurrentBlockState().getPos()));
            this.itemStack = ItemAirStack.AIR_STACK;
        }
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putCompound(TAG_ITEM, itemStack.saveNBT());
        builder.putByte(TAG_ITEM_ROTATION, itemRotation);
        // Item drop chance is always 1.0f in vanilla
        builder.putFloat(TAG_ITEM_DROP_CHANCE, 1.0f);
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // Should use setItemStack() here, since this method will set ITEM_FRAME_MAP_BIT to true if the item stack is a map
        nbt.listenForCompound(TAG_ITEM, itemNbt -> this.setItemStack(ItemHelper.fromNBT(itemNbt)));
        nbt.listenForByte(TAG_ITEM_ROTATION, itemRotation -> this.itemRotation = itemRotation);
    }

    @Override
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        if (this.itemStack instanceof ItemFilledMapStack) {
            this.position.dimension().updateBlockProperty(BlockPropertyTypes.ITEM_FRAME_MAP_BIT, true, this.position);
        } else {
            this.position.dimension().updateBlockProperty(BlockPropertyTypes.ITEM_FRAME_MAP_BIT, false, this.position);
        }
        sendBlockEntityDataPacketToViewers();
    }

    @Override
    public @Range(from = 0, to = 7) byte getItemRotation() {
        return this.itemRotation;
    }

    @Override
    public void setItemRotation(@Range(from = 0, to = 7) byte itemRotation) {
        this.itemRotation = itemRotation;
        sendBlockEntityDataPacketToViewers();
    }
}
