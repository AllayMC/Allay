package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.action.ContinueBreakAction;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.action.StartBreakAction;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.eventbus.event.player.PlayerJumpEvent;
import org.allaymc.api.eventbus.event.player.PlayerPunchAirEvent;
import org.allaymc.api.eventbus.event.player.PlayerPunchBlockEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.permission.Tristate;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.particle.PunchBlockParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
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

    private static boolean isInvalidGameType(Player player) {
        var entity = player.getControlledEntity();
        // Creative mode player can break blocks just like they are in
        // survival mode if "delayed block breaking" option is enabled
        // TODO: implement canBreak & canPlace feature
        return entity.getGameMode() == GameMode.ADVENTURE ||
               entity.getGameMode() == GameMode.SPECTATOR;
    }

    protected void handleMovement(Player player, Vector3f newPos, Vector3f newRot) {
        var entity = player.getControlledEntity();
        var world = entity.getLocation().dimension();
        ((AllayEntityPhysicsEngine) world.getEntityManager().getPhysicsService()).offerClientMove(entity, new Location3d(
                newPos.getX(), newPos.getY(), newPos.getZ(),
                newRot.getX(), newRot.getY(), world
        ));
    }

    protected void handleBlockAction(Player player, List<PlayerBlockActionData> blockActions, long time) {
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
            // Check interact distance
            switch (action.getAction()) {
                case START_BREAK, BLOCK_CONTINUE_DESTROY -> {
                    if (!player.getControlledEntity().canReachBlock(NetworkHelper.fromNetwork(pos))) {
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

    protected void startBreak(Player player, int x, int y, int z, int blockFaceId, long startBreakingTime) {
        var entity = player.getControlledEntity();
        var dimension = entity.getDimension();
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
            var event = new BlockBreakEvent(new Block(sideBlock, new Position3i(sidePos, dimension)), entity.getItemInHand(), entity);
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
        var event = new PlayerPunchBlockEvent(entity, block, faceToBreak);
        if (event.call()) {
            this.blockToBreak.getBlockType().getBlockBehavior().onPunch(block, faceToBreak, entity.getItemInHand(), entity);
        }

        if (entity.getGameMode() != GameMode.CREATIVE) {
            this.breakTime = this.blockToBreak.getBlockType().getBlockBehavior().calculateBreakTime(this.blockToBreak, entity.getItemInHand(), entity);
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

    protected void stopBreak(Player player) {
        if (this.blockToBreak == null) {
            log.debug("Player {} tried to stop breaking a block while not breaking any block", player.getOriginName());
            return;
        }

        player.getControlledEntity().getDimension().addBlockAction(this.breakingPosX, this.breakingPosY, this.breakingPosZ, SimpleBlockAction.STOP_BREAK);
        this.breakingPosX = Integer.MAX_VALUE;
        this.breakingPosY = Integer.MAX_VALUE;
        this.breakingPosZ = Integer.MAX_VALUE;
        this.faceToBreak = null;
        this.blockToBreak = null;
        this.breakTime = 0;
        this.stopBreakingTime = 0;
    }

    protected void completeBreak(Player player, int x, int y, int z) {
        if (this.breakingPosX != x || this.breakingPosY != y || this.breakingPosZ != z) {
            log.debug("Player {} tried to complete breaking a different block", player.getOriginName());
            return;
        }

        var entity = player.getControlledEntity();
        var currentTime = entity.getWorld().getTick();
        if (Math.abs(currentTime - this.stopBreakingTime) <= BLOCK_BREAKING_TIME_FAULT_TOLERANCE) {
            var world = entity.getDimension();
            var itemInHand = entity.getItemInHand();
            world.breakBlock(this.breakingPosX, this.breakingPosY, this.breakingPosZ, itemInHand, entity);
            itemInHand.onBreakBlock(this.blockToBreak, entity);
            if (itemInHand.isBroken()) {
                entity.clearItemInHand();
            } else {
                entity.notifyItemInHandChange();
            }
        } else {
            log.debug("Mismatch block breaking complete time! Expected: {}gt, actual: {}gt", this.stopBreakingTime, currentTime);
        }

        stopBreak(player);
    }

    protected boolean checkInteractDistance(Player player) {
        if (!player.getControlledEntity().canReach(this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f)) {
            log.debug("Player {} tried to interact with a block out of reach", player.getOriginName());
            stopBreak(player);
            return false;
        }

        return true;
    }

    protected void updateBreakingTime(Player player, long currentTime) {
        var entity = player.getControlledEntity();
        var newBreakingTime = this.blockToBreak.getBehavior().calculateBreakTime(this.blockToBreak, entity.getItemInHand(), entity);
        if (this.breakTime == newBreakingTime) {
            return;
        }

        // Breaking time has changed, make adjustments
        var timeLeft = this.stopBreakingTime - currentTime;
        this.stopBreakingTime = currentTime + timeLeft * (this.breakTime / newBreakingTime);
        this.breakTime = newBreakingTime;
    }

    protected void handleInputData(Player player, Set<PlayerAuthInputData> inputData) {
        var entity = player.getControlledEntity();
        if (entity.isDead()) {
            return;
        }

        for (var input : inputData) {
            switch (input) {
                case START_SPRINTING -> {
                    if (entity.getFoodLevel() <= 6) {
                        // TODO: stop client-side sprinting state
                        log.warn("Player {} tried to start sprinting without enough food level", player.getOriginName());
                        return;
                    }

                    entity.setSprinting(true);
                }
                case STOP_SPRINTING -> entity.setSprinting(false);
                case START_SNEAKING -> entity.setSneaking(true);
                case STOP_SNEAKING -> entity.setSneaking(false);
                case START_SWIMMING -> entity.setSwimming(true);
                case STOP_SWIMMING -> entity.setSwimming(false);
                case START_GLIDING -> entity.setGliding(true);
                case STOP_GLIDING -> entity.setGliding(false);
                case START_CRAWLING -> entity.setCrawling(true);
                case STOP_CRAWLING -> entity.setCrawling(false);
                case START_JUMPING -> {
                    new PlayerJumpEvent(entity).call();
                    entity.exhaust(entity.isSprinting() ? 0.2f : 0.05f);
                }
                case START_FLYING -> {
                    var gameMode = entity.getGameMode();
                    var tristate = entity.hasPermission(Permissions.ABILITY_FLY);
                    if (tristate == Tristate.FALSE ||
                        (gameMode != GameMode.CREATIVE && gameMode != GameMode.SPECTATOR && !tristate.asBoolean())) {

                        // Reset client-side flying state
                        var controller = entity.getController();
                        controller.viewPlayerPermission(controller);

                        log.warn("Player {} tried to start flying without permission", player.getOriginName());
                        return;
                    }

                    entity.setFlying(true);
                }
                case STOP_FLYING -> entity.setFlying(false);
                case MISSED_SWING -> new PlayerPunchAirEvent(entity).call();
            }
        }
    }

    @Override
    public void handleSync(Player player, PlayerAuthInputPacket packet, long receiveTime) {
        handleInputData(player, packet.getInputData());
        handleSingleItemStackRequest(player, packet.getItemStackRequest(), receiveTime);
    }

    @Override
    public PacketSignal handleAsync(Player player, PlayerAuthInputPacket packet, long receiveTime) {
        if (notReadyForInput(player)) {
            return PacketSignal.HANDLED;
        }

        var baseComponent = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player.getControlledEntity()).getBaseComponent());
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
            var dimension = player.getControlledEntity().getDimension();
            dimension.addParticle(
                    this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f,
                    new PunchBlockParticle(this.blockToBreak, this.faceToBreak)
            );
            updateBreakingTime(player, receiveTime);
            dimension.addBlockAction(
                    this.breakingPosX, this.breakingPosY, this.breakingPosZ,
                    new ContinueBreakAction(this.breakTime)
            );
        }
        return PacketSignal.UNHANDLED;
    }

    protected boolean validateClientLocation(Player player, Vector3f pos, Vector3f rot) {
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

    protected boolean isLocationChanged(Player player, Vector3f pos, Vector3f rot) {
        // The PlayerAuthInput packet is sent every tick, so don't do anything if the position and rotation were unchanged.
        var location = player.getControlledEntity().getLocation();
        return Double.compare(location.x(), pos.getX()) != 0 || Double.compare(location.y() + PLAYER_NETWORK_OFFSET, pos.getY()) != 0 || Double.compare(location.z(), pos.getZ()) != 0 ||
               Double.compare(location.pitch(), rot.getX()) != 0 || Double.compare(location.yaw(), rot.getY()) != 0;
    }

    protected void handleSingleItemStackRequest(Player player, ItemStackRequest request, long receiveTime) {
        // We had no idea why the client still uses PlayerAuthInputPacket to hold ItemStackRequest
        // Instead of using ItemStackRequestPacket
        // This seems to only happen when player breaks a block (MineBlockAction)
        if (request == null) {
            return;
        }

        var pk = new ItemStackRequestPacket();
        pk.getRequests().add(request);
        // Forward it to ItemStackRequestPacketProcessor
        ((AllayPlayer) player).handlePacketSync(pk, receiveTime);
    }

    protected boolean notReadyForInput(Player player) {
        var entity = player.getControlledEntity();
        return !player.isInitialized() || entity == null || !entity.isSpawned() || entity.willBeDespawnedNextTick();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_AUTH_INPUT;
    }
}
