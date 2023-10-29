1.20.31

1.从创造物品栏拿取木板到hotbar第一格  
Take the plank item from the Creative Inventory to the first hotbar

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-23, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-23), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-23, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=10, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-25, actions=[PlaceAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=10), destination=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-25, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=HOTBAR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=10, customName=, durabilityCorrection=0)])])])
```

2.从创造创造物品栏拿取木板到已经存在木板物品的hotbar第一格  
这在手机端和win10端会出现不同的发包行为，手机端物品看起来被替换，win10端物品看起来没变化  
Take the plank item from the Creative Inventory to the first hotbar that already has the item
This will have different packet behaviors on mobile and Win10 devices. On mobile, items appear to be replaced, while on
Win10, items appear unchanged.
win10:

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-31, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-31), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-31, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=12, customName=, durabilityCorrection=0)])])])
```

mobile:

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-7, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), DestroyAction(count=64, source=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=2)), PlaceAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-7), destination=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=-7))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-7, containers=[ItemStackResponseContainer(container=HOTBAR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=3, customName=, durabilityCorrection=0)])])])
```

3.从创造物品栏拿取云杉木板到已经存在木板物品的hotbar第一格  
From the Creative Inventory, take the spruce planks item to the first hotbar, where a plank item already exists

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-53, actions=[CraftCreativeAction(creativeItemNetworkId=2), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10431], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-53), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-53, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=16, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-55, actions=[SwapAction(source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=16), destination=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=14))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-55, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=14, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=HOTBAR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=16, customName=, durabilityCorrection=0)])])])
```

4.移动木板物品从第9格到第10格  
Move the plank item from slot 9 to slot 10

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-115, actions=[TakeAction(count=64, source=ItemStackRequestSlotData(container=INVENTORY, slot=9, stackNetworkId=19), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-115, containers=[ItemStackResponseContainer(container=INVENTORY, items=[ItemStackResponseSlot(slot=9, hotbarSlot=9, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=19, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-117, actions=[PlaceAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=19), destination=ItemStackRequestSlotData(container=INVENTORY, slot=10, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-117, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=INVENTORY, items=[ItemStackResponseSlot(slot=10, hotbarSlot=10, count=64, stackNetworkId=19, customName=, durabilityCorrection=0)])])])
```

5.移动物品到创造栏销毁物品  
Move an item to the Creative Inventory to destroy it

```
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-137, actions=[DestroyAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=19))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-137, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)])])])
```

6.从创造物品栏拿取木板到已经存在木板的hotbar第一格，然后丢弃该木板  
Take the plank from the Creative Inventory to the first hotbar where the plank already exists, then drop the plank

```
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-557, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-557), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-557, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=8, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-559, actions=[DropAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=8), randomly=false)], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-559, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)])])])
```