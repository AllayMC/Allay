package org.allaymc.server.blockentity.component.sign;

import lombok.Getter;
import org.allaymc.api.block.component.event.CBlockOnInteractEvent;
import org.allaymc.api.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.api.data.BlockFace;
import org.allaymc.api.blockentity.component.BlockEntitySignBaseComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.data.CompassRoseDirection;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.packet.OpenSignPacket;

/**
 * Allay Project 2024/8/16
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntitySignBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntitySignBaseComponent {

    protected SignText frontText = new SignTextImpl();
    protected SignText backText = new SignTextImpl();
    protected boolean waxed = false;

    public BlockEntitySignBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForBoolean("IsWaxed", this::setWaxed);
        nbt.listenForCompound("FrontText", value -> frontText = readSignTextFromNBT(value));
        nbt.listenForCompound("BackText", value -> backText = readSignTextFromNBT(value));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putBoolean("IsWaxed", waxed)
                .putCompound("FrontText", frontText.saveNBT())
                .putCompound("BackText", backText.saveNBT())
                .build();
    }

    @Override
    public void openSignEditorFor(EntityPlayer player, boolean frontSide) {
        var pk = new OpenSignPacket();
        pk.setPosition(MathUtils.JOMLVecToCBVec(getPosition()));
        pk.setFrontSide(frontSide);

        player.sendPacket(pk);
    }

    @Override
    public boolean isWaxed() {
        return waxed;
    }

    @Override
    public void setWaxed(boolean waxed) {
        this.waxed = waxed;
        sendBlockEntityDataPacketToViewers();
    }

    @Override
    public void onInteract(CBlockOnInteractEvent event) {
        super.onInteract(event);
        var player = event.getInteractInfo().player();
        if (player == null || player.isSneaking()) return;

        var isFrontSideInteracted = isFrontSideInteracted(event.getInteractInfo().blockFace());
        var itemInHand = player.getItemInHand();

        if (itemInHand.getItemType() == ItemTypes.HONEYCOMB && !waxed) {
            setWaxed(true);
            player.tryConsumeItemInHand();
            player.getDimension().addLevelEvent(position.x(), position.y(), position.z(), LevelEvent.PARTICLE_WAX_ON);
            sendBlockEntityDataPacketToViewers();
            event.setSuccess(true);
            return;
        }

        if (itemInHand.getItemType() == ItemTypes.GLOW_INK_SAC && !(isFrontSideInteracted ? frontText.isGlowing() : backText.isGlowing())) {
            if (isFrontSideInteracted) {
                frontText.setGlowing(true);
            } else {
                backText.setGlowing(true);
            }
            player.tryConsumeItemInHand();
            player.getDimension().addLevelEvent(player.getLocation(), LevelEvent.SOUND_INK_SACE_USED);
            sendBlockEntityDataPacketToViewers();
            event.setSuccess(true);
            return;
        }

        if (itemInHand.getItemType() == ItemTypes.INK_SAC && isFrontSideInteracted ? frontText.isGlowing() : backText.isGlowing()) {
            if (isFrontSideInteracted) {
                frontText.setGlowing(false);
            } else {
                backText.setGlowing(false);
            }
            player.tryConsumeItemInHand();
            player.getDimension().addLevelEvent(player.getLocation(), LevelEvent.SOUND_INK_SACE_USED);
            sendBlockEntityDataPacketToViewers();
            event.setSuccess(true);
            return;
        }

        // If a sign is waxed, it cannot be modified.
        if (waxed) return;
        openSignEditorFor(player, isFrontSideInteracted);
        event.setSuccess(true);
    }

    @Override
    public void onPlace(CBlockOnPlaceEvent event) {
        super.onPlace(event);
        if (event.getPlacementInfo() == null) return;
        openSignEditorFor(event.getPlacementInfo().player(), true);
    }

    protected boolean isFrontSideInteracted(BlockFace interactedFace) {
        var signDirection = CompassRoseDirection.from(getBlockState().getPropertyValue(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION));
        return switch (signDirection) {
            case EAST -> interactedFace == BlockFace.EAST;
            case SOUTH -> interactedFace == BlockFace.SOUTH;
            case WEST -> interactedFace == BlockFace.WEST;
            case NORTH -> interactedFace == BlockFace.NORTH;
            case NORTH_EAST, NORTH_NORTH_EAST, EAST_NORTH_EAST -> interactedFace == BlockFace.EAST || interactedFace == BlockFace.NORTH;
            case NORTH_WEST, NORTH_NORTH_WEST, WEST_NORTH_WEST -> interactedFace == BlockFace.WEST || interactedFace == BlockFace.NORTH;
            case SOUTH_EAST, SOUTH_SOUTH_EAST, EAST_SOUTH_EAST -> interactedFace == BlockFace.EAST || interactedFace == BlockFace.SOUTH;
            case SOUTH_WEST, SOUTH_SOUTH_WEST, WEST_SOUTH_WEST -> interactedFace == BlockFace.WEST || interactedFace == BlockFace.SOUTH;
        };
    }

    protected SignText readSignTextFromNBT(NbtMap nbt) {
        var signText = new SignTextImpl();

        nbt.listenForString("Text", value -> {
            signText.text = AllayStringUtils.fastSplit(value, "\n", 4).toArray(String[]::new);
        });
        nbt.listenForBoolean("IgnoreLighting", value -> signText.glowing = value);

        return signText;
    }

    protected class SignTextImpl implements SignText {

        protected String[] text = new String[]{""};
        protected boolean glowing = false;
        // TODO: Color

        @Override
        public String[] getText() {
            return text;
        }

        @Override
        public void setText(String[] text) {
            this.text = text;
            sendBlockEntityDataPacketToViewers();
        }

        @Override
        public boolean isGlowing() {
            return glowing;
        }

        @Override
        public void setGlowing(boolean glowing) {
            this.glowing = glowing;
            sendBlockEntityDataPacketToViewers();
        }

        @Override
        public NbtMap saveNBT() {
            return NbtMap.builder()
                    .putString("Text", String.join("\n", text))
                    .putBoolean("IgnoreLighting", glowing)
                    // Not implemented
                    .putInt("SignTextColor", -16777216)
                    // true if the outer glow of a sign with glowing text does not show.
                    .putBoolean("HideGlowOutline", false)
                    // Unknown. Maybe save formatting character like §?
                    .putBoolean("PersistFormatting", true)
                    // Unknown. The player who placed the sign?
                    .putString("TextOwner", "")
                    .build();
        }
    }
}
