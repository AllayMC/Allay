---
comments: true
---

Game version: 1.20.81

System: Windows

## Creative Mode

### Single Left Click

Packet Details:

```text
[00:14:07:261] [SERVER BOUND] - PlayerActionPacket(runtimeEntityId=1, action=DIMENSION_CHANGE_REQUEST_OR_CREATIVE_DESTROY_BLOCK, blockPosition=(15, -61, 1), resultPosition=(0, 0, 0), face=2)
[00:14:07:275] [SERVER BOUND] - PlayerAuthInputPacket(rotation=(27.348877, 3.0321198, 3.0321198), position=(15.550625, -58.37999, -3.3560686), motion=(0.0, 0.0), inputData=[PERFORM_BLOCK_ACTIONS], inputMode=MOUSE, playMode=SCREEN, vrGazeDirection=null, tick=5765, delta=(-0.0, -0.0784, 0.0), itemUseTransaction=null, itemStackRequest=null, playerActions=[PlayerBlockActionData(action=START_BREAK, blockPosition=(15, -61, 1), face=2), PlayerBlockActionData(action=BLOCK_PREDICT_DESTROY, blockPosition=(15, -61, 1), face=2)], inputInteractionModel=TOUCH, analogMoveVector=(0.0, 0.0), predictedVehicle=0, vehicleRotation=null)
[00:14:07:368] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_DESTROY_BLOCK, position=(15.5, -60.5, 1.5), data=10598)
[00:14:07:398] [CLIENT BOUND] - UpdateBlockPacket(flags=[NEIGHBORS, NETWORK], blockPosition=(15, -61, 1), definition=UnknownDefinition[runtimeId=11881], dataLayer=0)
```

When destroying blocks in creative mode, the server
receives `PlayerActionPacket(runtimeEntityId=16, action=DIMENSION_CHANGE_REQUEST_OR_CREATIVE_DESTROY_BLOCK, ...)`.

The `data` field in `LevelEventPacket(type=PARTICLE_DESTROY_BLOCK, position=(3.5, -60.5, -1.5), data=10598)` actually
refers to the block but uses the old network ID (it's unclear why the new BlockStateHash is not used).
`PARTICLE_DESTROY_BLOCK` refers to the particles and sound when a block is completely destroyed (drops).
Sending this `LevelEvent` to the client will trigger particles and sound.

Note: 50342246 and 10598 are the BlockStateHash and the old network ID of `minecraft:grass_block` respectively.

## Survival Mode

### Hold and Destroy a Block

Packet Details:

```text
[00:09:33:083] [SERVER BOUND] - PlayerAuthInputPacket(rotation=(20.417938, 4.530777, 4.530777), position=(15.700001, -58.37999, -6.589204), motion=(0.0, 0.0), inputData=[PERFORM_BLOCK_ACTIONS], inputMode=MOUSE, playMode=SCREEN, vrGazeDirection=null, tick=281, delta=(0.0, -0.0784, 0.0), itemUseTransaction=null, itemStackRequest=null, playerActions=[PlayerBlockActionData(action=START_BREAK, blockPosition=(15, -60, -4), face=2)], inputInteractionModel=TOUCH, analogMoveVector=(0.0, 0.0), predictedVehicle=0, vehicleRotation=null)
[00:09:33:176] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_START_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:176] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:176] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:224] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:224] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:270] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:270] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:317] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:317] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:379] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:379] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:425] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:425] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:472] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:472] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:519] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:519] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:551] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:551] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:629] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:629] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:674] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:674] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:722] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:722] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:768] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:768] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:784] [SERVER BOUND] - PlayerAuthInputPacket(rotation=(20.417938, 4.530777, 4.530777), position=(15.700001, -58.37999, -6.589204), motion=(0.0, 0.0), inputData=[PERFORM_BLOCK_ACTIONS], inputMode=MOUSE, playMode=SCREEN, vrGazeDirection=null, tick=295, delta=(0.0, -0.0784, 0.0), itemUseTransaction=null, itemStackRequest=null, playerActions=[PlayerBlockActionData(action=BLOCK_CONTINUE_DESTROY, blockPosition=(15, -60, -4), face=2), PlayerBlockActionData(action=BLOCK_PREDICT_DESTROY, blockPosition=(15, -60, -4), face=2)], inputInteractionModel=TOUCH, analogMoveVector=(0.0, 0.0), predictedVehicle=0, vehicleRotation=null)
[00:09:33:832] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(15.5, -59.5, -3.5), data=33561418)
[00:09:33:832] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:878] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_STOP_BREAK, position=(15.0, -60.0, -4.0), data=0)
[00:09:33:878] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_START_BREAK, position=(15.0, -60.0, -4.0), data=4369)
[00:09:33:878] [CLIENT BOUND] - LevelEventPacket(type=PARTICLE_DESTROY_BLOCK, position=(15.5, -59.5, -3.5), data=6986)
[00:09:33:971] [SERVER BOUND] - PlayerAuthInputPacket(rotation=(20.417938, 4.530777, 4.530777), position=(15.700001, -58.37999, -6.589204), motion=(0.0, 0.0), inputData=[PERFORM_BLOCK_ACTIONS], inputMode=MOUSE, playMode=SCREEN, vrGazeDirection=null, tick=299, delta=(0.0, -0.0784, 0.0), itemUseTransaction=null, itemStackRequest=null, playerActions=[PlayerBlockActionData(action=ABORT_BREAK, blockPosition=(15, -60, -4), face=0)], inputInteractionModel=TOUCH, analogMoveVector=(0.0, 0.0), predictedVehicle=0, vehicleRotation=null)
[00:09:34:078] [CLIENT BOUND] - LevelEventPacket(type=BLOCK_STOP_BREAK, position=(15.0, -60.0, -4.0), data=0)

```

The `data` field in `LevelEventPacket(type=BLOCK_UPDATE_BREAK, position=(15.0, -61.0, -8.0), data=3640)` indicates the
time required to mine.
The formula is

$$
time = \frac{6553.5}{\text{required mining seconds}}
$$

The data field in `LevelEventPacket(type=PARTICLE_CRACK_BLOCK, position=(3.5, -60.5, -1.5), data=50342246)` indicates
the block's BlockStateHash.
`PARTICLE_CRACK_BLOCK` refers to the particles coming off the block surface during breaking.