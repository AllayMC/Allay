---
comments: true
---

Game Version: 1.21.0

### Drop a cherry plank without opening the inventory (i.e., press the Q key)

Packet details:

```txt
[22:40:09:635] [SERVER BOUND] - InventoryTransactionPacket(
  legacyRequestId=-12, 
  legacySlots=[LegacySetItemSlotData(containerId=29, slots=[0])], 
  actions=[
    InventoryActionData(
      source=InventorySource(type=WORLD_INTERACTION, containerId=-1, flag=DROP_ITEM), 
      slot=0, 
      fromItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:air, runtimeId=0, componentBased=false), damage=0, count=0, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=null, usingNetId=false, netId=0), 
      toItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:cherry_planks, runtimeId=-537, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=13478], usingNetId=false, netId=0), 
      stackNetworkId=0), 
    InventoryActionData(
      source=InventorySource(type=CONTAINER, containerId=0, flag=NONE),
      slot=0, 
      fromItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:cherry_planks, runtimeId=-537, componentBased=false), damage=0, count=64, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=13478], usingNetId=false, netId=0), 
      toItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:cherry_planks, runtimeId=-537, componentBased=false), damage=0, count=63, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=13478], usingNetId=false, netId=0), 
      stackNetworkId=0)
  ], 
  transactionType=NORMAL, 
  actionType=0, 
  runtimeEntityId=0, 
  blockPosition=null,
  blockFace=0, 
  hotbarSlot=0, 
  itemInHand=null, 
  playerPosition=null, 
  clickPosition=null, 
  headPosition=null, 
  usingNetIds=false, 
  blockDefinition=null
)
[22:40:09:649] [SERVER BOUND] - MobEquipmentPacket(runtimeEntityId=2, item=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:cherry_planks, runtimeId=-537, componentBased=false), damage=0, count=63, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=13478], usingNetId=false, netId=0), inventorySlot=0, hotbarSlot=0, containerId=0)
[22:40:09:742] [CLIENT BOUND] - AddItemEntityPacket(metadata={FLAGS=[HAS_COLLISION, HAS_GRAVITY], STRUCTURAL_INTEGRITY=1i, VARIANT=0i, BLOCK=UnknownDefinition[runtimeId=0], COLOR=0b, NAME="", OWNER_EID=-176093659135l, AIR_SUPPLY=300s, CHARGE_AMOUNT=0b, LEASH_HOLDER=0l, SCALE=1.0f, HAS_NPC=false, AIR_SUPPLY_MAX=300s, MARK_VARIANT=0i, CONTAINER_TYPE=0b, CONTAINER_SIZE=0i, CONTAINER_STRENGTH_MODIFIER=0i, WIDTH=0.25f, HEIGHT=0.25f, SEAT_OFFSET=(0.0, 0.0, 0.0), SEAT_LOCK_RIDER_ROTATION=false, SEAT_LOCK_RIDER_ROTATION_DEGREES=0.0f, SEAT_HAS_ROTATION=false, SEAT_ROTATION_OFFSET_DEGREES=0.0f, COMMAND_BLOCK_ENABLED=false, COMMAND_BLOCK_NAME="", COMMAND_BLOCK_LAST_OUTPUT="", COMMAND_BLOCK_TRACK_OUTPUT=true, STRENGTH=0i, STRENGTH_MAX=0i, EVOKER_SPELL_CASTING_COLOR=0i, DATA_LIFETIME_TICKS=-1i, NAMETAG_ALWAYS_SHOW=-1b, COLOR_2=0b, TRADE_TIER=0i, MAX_TRADE_TIER=0i, TRADE_EXPERIENCE=0i, SKIN_ID=0i, COMMAND_BLOCK_TICK_DELAY=3i, COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK=true, AMBIENT_SOUND_INTERVAL=8.0f, AMBIENT_SOUND_INTERVAL_RANGE=16.0f, AMBIENT_SOUND_EVENT_NAME="ambient", FALL_DAMAGE_MULTIPLIER=1.0f, CAN_RIDE_TARGET=false, LOW_TIER_CURED_TRADE_DISCOUNT=0i, HIGH_TIER_CURED_TRADE_DISCOUNT=0i, NEARBY_CURED_TRADE_DISCOUNT=0i, NEARBY_CURED_DISCOUNT_TIME_STAMP=0i, HITBOX={}, IS_BUOYANT=false, FREEZING_EFFECT_STRENGTH=0.0f, MOVEMENT_SOUND_DISTANCE_OFFSET=1.0f, HEARTBEAT_INTERVAL_TICKS=20i, HEARTBEAT_SOUND_EVENT=530i}, uniqueEntityId=-176093659134, runtimeEntityId=3, itemInHand=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:cherry_planks, runtimeId=-537, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=13478], usingNetId=false, netId=0), position=(8.462395, -56.75499, 1.4127765), motion=(0.1777136, -0.17729755, -0.18142024), fromFishing=false)
```

### Drop a non-full durability pickaxe without opening the inventory (i.e., press the Q key)

Packet details:

```txt
[22:45:09:510] [SERVER BOUND] - InventoryTransactionPacket(
  legacyRequestId=0,
  legacySlots=[], 
  actions=[
    InventoryActionData(
      source=InventorySource(type=WORLD_INTERACTION, containerId=-1, flag=DROP_ITEM), 
      slot=0, 
      fromItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:air, runtimeId=0, componentBased=false), damage=0, count=0, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=null, usingNetId=false, netId=0), 
      toItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:netherite_pickaxe, runtimeId=624, componentBased=false), damage=0, count=1, tag={"Damage": 1i}, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=0], usingNetId=false, netId=0), 
      stackNetworkId=0),
    InventoryActionData(
      source=InventorySource(type=CONTAINER, containerId=0, flag=NONE), 
      slot=1, 
      fromItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:netherite_pickaxe, runtimeId=624, componentBased=false), damage=0, count=1, tag={"Damage": 1i}, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=0], usingNetId=false, netId=0), 
      toItem=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:air, runtimeId=0, componentBased=false), damage=0, count=0, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=null, usingNetId=false, netId=0), 
      stackNetworkId=0)
  ],
  transactionType=NORMAL,
  actionType=0, 
  runtimeEntityId=0, 
  blockPosition=null, 
  blockFace=0, 
  hotbarSlot=0, 
  itemInHand=null, 
  playerPosition=null, 
  clickPosition=null, 
  headPosition=null, 
  usingNetIds=false, 
  blockDefinition=null
)
[22:45:09:556] [SERVER BOUND] - MobEquipmentPacket(runtimeEntityId=2, item=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:air, runtimeId=0, componentBased=false), damage=0, count=0, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=null, usingNetId=false, netId=0), inventorySlot=1, hotbarSlot=1, containerId=0)
[22:45:09:604] [CLIENT BOUND] - AddItemEntityPacket(metadata={FLAGS=[HAS_COLLISION, HAS_GRAVITY], STRUCTURAL_INTEGRITY=1i, VARIANT=0i, BLOCK=UnknownDefinition[runtimeId=0], COLOR=0b, NAME="", OWNER_EID=-176093659135l, AIR_SUPPLY=300s, CHARGE_AMOUNT=0b, LEASH_HOLDER=0l, SCALE=1.0f, HAS_NPC=false, AIR_SUPPLY_MAX=300s, MARK_VARIANT=0i, CONTAINER_TYPE=0b, CONTAINER_SIZE=0i, CONTAINER_STRENGTH_MODIFIER=0i, WIDTH=0.25f, HEIGHT=0.25f, SEAT_OFFSET=(0.0, 0.0, 0.0), SEAT_LOCK_RIDER_ROTATION=false, SEAT_LOCK_RIDER_ROTATION_DEGREES=0.0f, SEAT_HAS_ROTATION=false, SEAT_ROTATION_OFFSET_DEGREES=0.0f, COMMAND_BLOCK_ENABLED=false, COMMAND_BLOCK_NAME="", COMMAND_BLOCK_LAST_OUTPUT="", COMMAND_BLOCK_TRACK_OUTPUT=true, STRENGTH=0i, STRENGTH_MAX=0i, EVOKER_SPELL_CASTING_COLOR=0i, DATA_LIFETIME_TICKS=-1i, NAMETAG_ALWAYS_SHOW=-1b, COLOR_2=0b, TRADE_TIER=0i, MAX_TRADE_TIER=0i, TRADE_EXPERIENCE=0i, SKIN_ID=0i, COMMAND_BLOCK_TICK_DELAY=3i, COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK=true, AMBIENT_SOUND_INTERVAL=8.0f, AMBIENT_SOUND_INTERVAL_RANGE=16.0f, AMBIENT_SOUND_EVENT_NAME="ambient", FALL_DAMAGE_MULTIPLIER=1.0f, CAN_RIDE_TARGET=false, LOW_TIER_CURED_TRADE_DISCOUNT=0i, HIGH_TIER_CURED_TRADE_DISCOUNT=0i, NEARBY_CURED_TRADE_DISCOUNT=0i, NEARBY_CURED_DISCOUNT_TIME_STAMP=0i, HITBOX={}, IS_BUOYANT=false, FREEZING_EFFECT_STRENGTH=0.0f, MOVEMENT_SOUND_DISTANCE_OFFSET=1.0f, HEARTBEAT_INTERVAL_TICKS=20i, HEARTBEAT_SOUND_EVENT=530i}, uniqueEntityId=-176093659132, runtimeEntityId=5, itemInHand=BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:netherite_pickaxe, runtimeId=624, componentBased=false), damage=0, count=1, tag={
  "Damage": 1i
}, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=0], usingNetId=false, netId=0), position=(9.715393, -58.75499, -0.12948078), motion=(0.22091694, -0.198854, -0.09380954), fromFishing=false)
```

