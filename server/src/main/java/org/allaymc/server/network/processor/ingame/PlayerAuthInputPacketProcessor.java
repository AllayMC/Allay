package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.action.ContinueBreakAction;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.action.StartBreakAction;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.eventbus.event.player.PlayerJumpEvent;
import org.allaymc.api.eventbus.event.player.PlayerPunchAirEvent;
import org.allaymc.api.eventbus.event.player.PlayerPunchBlockEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.particle.PunchBlockParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerClientComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.world.physics.AllayEntityPhysicsEngine;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.Set;

/**
 * @author Cool_Loong
 */
@Slf4j
public class PlayerAuthInputPacketProcessor extends PacketProcessor<PlayerAuthInputPacket> {

    // TODO: Accurate breaking time calculations when player keeping jumping, maybe use BLOCK_BREAKING_DELAY_ENABLED?
    // The current implementation will work fine in most cases
    // But it doesn't work out the same breaking time as the client when player keep jumping
    // It is hard for us to calculate the exact breaking time when player keep jumping
    protected static final int BLOCK_BREAKING_TIME_FAULT_TOLERANCE = Integer.MAX_VALUE;
    protected static final int TELEPORT_ACK_DIFF_TOLERANCE = 1;
    protected static final float PLAYER_NETWORK_OFFSET = 1.62f;

    protected int breakingPosX = Integer.MAX_VALUE;
    protected int breakingPosY = Integer.MAX_VALUE;
    protected int breakingPosZ = Integer.MAX_VALUE;

    protected BlockFace faceToBreak;
    protected BlockState blockToBreak;

    // Seconds
    protected double breakTime;
    // Ticks
    protected double stopBreakingTime;

    private static boolean isInvalidGameType(EntityPlayer player) {
        // Creative mode player can break blocks just like they are in
        // survival mode if "delayed block breaking" option is enabled
        // TODO: implement canBreak & canPlace feature
        return player.getGameMode() == GameMode.ADVENTURE ||
               player.getGameMode() == GameMode.SPECTATOR;
    }

    protected void handleMovement(EntityPlayer player, Vector3f newPos, Vector3f newRot) {
        var world = player.getLocation().dimension();
        ((AllayEntityPhysicsEngine) world.getEntityManager().getPhysicsService()).offerClientMove(player, new Location3d(
                newPos.getX(), newPos.getY(), newPos.getZ(),
                newRot.getX(), newRot.getY(), world
        ));
    }

    protected void handleBlockAction(EntityPlayer player, List<PlayerBlockActionData> blockActions, long time) {
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
            // Check interact distance
            switch (action.getAction()) {
                case START_BREAK, BLOCK_CONTINUE_DESTROY -> {
                    if (!player.canReachBlock(NetworkHelper.fromNetwork(pos))) {
                        log.debug("Player {} tried to break a block out of reach", player.getOriginName());
                        continue;
                    }
                }
            }

            switch (action.getAction()) {
                case START_BREAK -> {
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace(), time);
                }
                case BLOCK_CONTINUE_DESTROY -> {
                    // When a player switches to breaking another block halfway through breaking one
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    // HACK: The client for some reason sends a meaningless BLOCK_CONTINUE_DESTROY before BLOCK_PREDICT_DESTROY, presumably a bug, so ignore it here
                    if (this.breakingPosX == pos.getX() && this.breakingPosY == pos.getY() && this.breakingPosZ == pos.getZ()) {
                        continue;
                    }

                    stopBreak(player);
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace(), time);
                }
                case BLOCK_PREDICT_DESTROY -> {
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    completeBreak(player, pos.getX(), pos.getY(), pos.getZ());
                }
                case ABORT_BREAK -> {
                    // Digging interrupted
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    stopBreak(player);
                }
            }
        }
    }

    protected boolean isBreakingBlock() {
        return this.blockToBreak != null;
    }

    protected void startBreak(EntityPlayer player, int x, int y, int z, int blockFaceId, long startBreakingTime) {
        var dimension = player.getDimension();
        if (this.blockToBreak != null) {
            log.debug("Player {} tried to start breaking a block while already breaking one", player.getOriginName());
            stopBreak(player);
        }

        if (this.breakingPosX == x && this.breakingPosY == y && this.breakingPosZ == z) {
            log.debug("Player {} tried to start breaking the same block twice", player.getOriginName());
            return;
        }

        this.blockToBreak = dimension.getBlockState(x, y, z);
        this.faceToBreak = BlockFace.fromIndex(blockFaceId);
        if (this.faceToBreak == null) {
            log.debug("Player {} tried to break a block with an invalid face {}", player.getOriginName(), blockFaceId);
            this.blockToBreak = null;
            return;
        }

        // If there is fire in the face, try extinguishing it
        var sidePos = this.faceToBreak.offsetPos(x, y, z);
        var sideBlock = dimension.getBlockState(sidePos);
        if (sideBlock.getBlockType() == BlockTypes.FIRE || sideBlock.getBlockType() == BlockTypes.SOUL_FIRE) {
            var event = new BlockBreakEvent(new Block(sideBlock, new Position3i(sidePos, dimension)), player.getItemInHand(), player);
            if (!event.call()) {
                // Resend the block because on client side that was extinguished
                player.viewBlockUpdate(sidePos, 0, sideBlock);
            } else {
                dimension.setBlockState(sidePos, BlockTypes.AIR.getDefaultState());
                dimension.addSound(MathUtils.center(sidePos), SimpleSound.FIRE_EXTINGUISH);
            }
        }

        this.breakingPosX = x;
        this.breakingPosY = y;
        this.breakingPosZ = z;

        var block = new Block(blockToBreak, new Position3i(x, y, z, dimension));
        var event = new PlayerPunchBlockEvent(player, block, faceToBreak);
        if (event.call()) {
            this.blockToBreak.getBlockType().getBlockBehavior().onPunch(block, faceToBreak, player.getItemInHand(), player);
        }

        if (player.getGameMode() != GameMode.CREATIVE) {
            this.breakTime = this.blockToBreak.getBlockType().getBlockBehavior().calculateBreakTime(this.blockToBreak, player.getItemInHand(), player);
        } else {
            // Creative mode players can break blocks instantly
            this.breakTime = 0;
        }
        this.stopBreakingTime = startBreakingTime + this.breakTime * 20.0d;

        dimension.addBlockAction(x, y, z, new StartBreakAction(this.breakTime));
        dimension.addParticle(
                this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f,
                new PunchBlockParticle(this.blockToBreak, this.faceToBreak)
        );
    }

    protected void stopBreak(EntityPlayer player) {
        if (this.blockToBreak == null) {
            log.debug("Player {} tried to stop breaking a block while not breaking any block", player.getOriginName());
            return;
        }

        player.getDimension().addBlockAction(this.breakingPosX, this.breakingPosY, this.breakingPosZ, SimpleBlockAction.STOP_BREAK);
        this.breakingPosX = Integer.MAX_VALUE;
        this.breakingPosY = Integer.MAX_VALUE;
        this.breakingPosZ = Integer.MAX_VALUE;
        this.faceToBreak = null;
        this.blockToBreak = null;
        this.breakTime = 0;
        this.stopBreakingTime = 0;
    }

    protected void completeBreak(EntityPlayer player, int x, int y, int z) {
        if (this.breakingPosX != x || this.breakingPosY != y || this.breakingPosZ != z) {
            log.debug("Player {} tried to complete breaking a different block", player.getOriginName());
            return;
        }

        var currentTime = player.getWorld().getTick();
        if (Math.abs(currentTime - this.stopBreakingTime) <= BLOCK_BREAKING_TIME_FAULT_TOLERANCE) {
            var world = player.getDimension();
            var itemInHand = player.getItemInHand();
            world.breakBlock(this.breakingPosX, this.breakingPosY, this.breakingPosZ, itemInHand, player);
            itemInHand.onBreakBlock(this.blockToBreak, player);
            if (itemInHand.isBroken()) {
                player.clearItemInHand();
            } else {
                player.notifyItemInHandChange();
            }
        } else {
            log.debug("Mismatch block breaking complete time! Expected: {}gt, actual: {}gt", this.stopBreakingTime, currentTime);
        }

        stopBreak(player);
    }

    protected boolean checkInteractDistance(EntityPlayer player) {
        if (!player.canReach(this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f)) {
            log.debug("Player {} tried to interact with a block out of reach", player.getOriginName());
            stopBreak(player);
            return false;
        }

        return true;
    }

    protected void updateBreakingTime(EntityPlayer player, long currentTime) {
        var newBreakingTime = this.blockToBreak.getBehavior().calculateBreakTime(this.blockToBreak, player.getItemInHand(), player);
        if (this.breakTime == newBreakingTime) {
            return;
        }

        // Breaking time has changed, make adjustments
        var timeLeft = this.stopBreakingTime - currentTime;
        this.stopBreakingTime = currentTime + timeLeft * (this.breakTime / newBreakingTime);
        this.breakTime = newBreakingTime;
    }

    protected void handleInputData(EntityPlayer player, Set<PlayerAuthInputData> inputData) {
        if (player.isDead()) {
            return;
        }

        for (var input : inputData) {
            switch (input) {
                case START_SPRINTING -> player.setSprinting(true);
                case STOP_SPRINTING -> player.setSprinting(false);
                case START_SNEAKING -> player.setSneaking(true);
                case STOP_SNEAKING -> player.setSneaking(false);
                case START_SWIMMING -> player.setSwimming(true);
                case STOP_SWIMMING -> player.setSwimming(false);
                case START_GLIDING -> player.setGliding(true);
                case STOP_GLIDING -> player.setGliding(false);
                case START_CRAWLING -> player.setCrawling(true);
                case STOP_CRAWLING -> player.setCrawling(false);
                case START_JUMPING -> {
                    new PlayerJumpEvent(player).call();
                    player.exhaust(player.isSprinting() ? 0.2f : 0.05f);
                }
                case START_FLYING -> {
                    if (!player.hasPermission(Permissions.ABILITY_FLY)) {
                        player.setFlying(false);
                        log.warn("Player {} tried to start flying without permission", player.getOriginName());
                        return;
                    }

                    player.setFlying(true);
                }
                case STOP_FLYING -> player.setFlying(false);
                case MISSED_SWING -> new PlayerPunchAirEvent(player).call();
            }
        }
    }

    @Override
    public void handleSync(EntityPlayer player, PlayerAuthInputPacket packet, long receiveTime) {
        handleInputData(player, packet.getInputData());
        handleSingleItemStackRequest(player, packet.getItemStackRequest(), receiveTime);
    }

    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerAuthInputPacket packet, long receiveTime) {
        if (notReadyForInput(player)) {
            return PacketSignal.HANDLED;
        }

        var baseComponent = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent());
        if (baseComponent.getExpectedTeleportPos() != null) {
            var clientPos = NetworkHelper.fromNetwork(packet.getPosition().sub(0, PLAYER_NETWORK_OFFSET, 0));
            var diff = baseComponent.getExpectedTeleportPos().sub(clientPos.x(), clientPos.y(), clientPos.z(), new org.joml.Vector3d()).length();
            if (diff > TELEPORT_ACK_DIFF_TOLERANCE) {
                // The player has moved before it received the teleport packet. Ignore this movement entirely and
                // wait for the client to sync itself back to the server. Once we get a movement that is close
                // enough to the teleport position, we'll allow the player to move around again.
                return PacketSignal.HANDLED;
            }
            baseComponent.setExpectedTeleportPos(null);
        }

        if (!validateClientLocation(player, packet.getPosition(), packet.getRotation())) {
            // Ignore this auth packet if the pos provided by client is not valid
            return PacketSignal.HANDLED;
        }

        if (isLocationChanged(player, packet.getPosition(), packet.getRotation())) {
            // The pos which client sends to the server is higher than the actual coordinates (one base offset)
            handleMovement(player, packet.getPosition().sub(0, PLAYER_NETWORK_OFFSET, 0), packet.getRotation());
        }
        handleBlockAction(player, packet.getPlayerActions(), receiveTime);
        if (isBreakingBlock() && checkInteractDistance(player)) {
            player.getDimension().addParticle(
                    this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f,
                    new PunchBlockParticle(this.blockToBreak, this.faceToBreak)
            );
            updateBreakingTime(player, receiveTime);
            player.getDimension().addBlockAction(
                    this.breakingPosX, this.breakingPosY, this.breakingPosZ,
                    new ContinueBreakAction(this.breakTime)
            );
        }
        return PacketSignal.UNHANDLED;
    }

    protected boolean validateClientLocation(EntityPlayer player, Vector3f pos, Vector3f rot) {
        var valid = !Float.isNaN(pos.getX()) && !Float.isNaN(pos.getY()) && !Float.isNaN(pos.getZ()) &&
                    !Float.isNaN(rot.getX()) && !Float.isNaN(rot.getY()) && !Float.isNaN(rot.getZ());
        if (!valid) {
            // Sometimes, the PlayerAuthInput packet is in fact sent with NaN/INF after being teleported (to another
            // world). For this reason, we don't actually return an error if this happens, because this will result
            // in the player being kicked. Just log it and replace the NaN value with the one we have tracked server-side.
            log.debug("Found NaN in PlayerAuthInputPacket sent by player {}", player.getOriginName());
        }
        return valid;
    }

    protected boolean isLocationChanged(EntityPlayer player, Vector3f pos, Vector3f rot) {
        // The PlayerAuthInput packet is sent every tick, so don't do anything if the position and rotation were unchanged.
        var location = player.getLocation();
        return Double.compare(location.x(), pos.getX()) != 0 || Double.compare(location.y() + PLAYER_NETWORK_OFFSET, pos.getY()) != 0 || Double.compare(location.z(), pos.getZ()) != 0 ||
               Double.compare(location.pitch(), rot.getX()) != 0 || Double.compare(location.yaw(), rot.getY()) != 0;
    }

    protected void handleSingleItemStackRequest(EntityPlayer player, ItemStackRequest request, long receiveTime) {
        // We had no idea why the client still use PlayerAuthInputPacket to hold ItemStackRequest
        // Instead of using ItemStackRequestPacket
        // This seems only happen when player break a block (MineBlockAction)
        if (request == null) {
            return;
        }

        var pk = new ItemStackRequestPacket();
        pk.getRequests().add(request);
        // Forward it to ItemStackRequestPacketProcessor
        ((EntityPlayerClientComponentImpl) ((EntityPlayerImpl) player).getPlayerClientComponent()).handlePacketSync(pk, receiveTime);
    }

    protected boolean notReadyForInput(EntityPlayer player) {
        return !player.isInitialized() || !player.isSpawned() || player.willBeDespawnedNextTick();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_AUTH_INPUT;
    }
}
