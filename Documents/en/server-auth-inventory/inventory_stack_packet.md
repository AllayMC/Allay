1.20.31

### Moving wooden planks from the crafting grid to the first slot of the hotbar:

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-23, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-23), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-23, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=10, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-25, actions=[PlaceAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=10), destination=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-25, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=HOTBAR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=10, customName=, durabilityCorrection=0)])])])
```

### Moving wooden planks from the crafting grid to the existing wooden plank in the first slot of the hotbar:

##### Win10

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-31, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-31), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-31, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=12, customName=, durabilityCorrection=0)])])])
```

##### Mobile

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-7, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), DestroyAction(count=64, source=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=2)), PlaceAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-7), destination=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=-7))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-7, containers=[ItemStackResponseContainer(container=HOTBAR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=3, customName=, durabilityCorrection=0)])])])
```

### Moving spruce wooden planks from the crafting grid to the existing wooden plank in the first slot of the hotbar:

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-53, actions=[CraftCreativeAction(creativeItemNetworkId=2), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10431], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-53), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-53, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=16, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-55, actions=[SwapAction(source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=16), destination=ItemStackRequestSlotData(container=HOTBAR, slot=0, stackNetworkId=14))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-55, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=14, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=HOTBAR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=16, customName=, durabilityCorrection=0)])])])
```

### Moving wooden planks from the ninth slot to the 10th slot:

```txt
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-115, actions=[TakeAction(count=64, source=ItemStackRequestSlotData(container=INVENTORY, slot=9, stackNetworkId=19), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-115, containers=[ItemStackResponseContainer(container=INVENTORY, items=[ItemStackResponseSlot(slot=9, hotbarSlot=9, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=19, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-117, actions=[PlaceAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=19), destination=ItemStackRequestSlotData(container=INVENTORY, slot=10, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-117, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)]), ItemStackResponseContainer(container=INVENTORY, items=[ItemStackResponseSlot(slot=10, hotbarSlot=10, count=64, stackNetworkId=19, customName=, durabilityCorrection=0)])])])
```

### Moving items to the crafting grid and then destroying the item:

```
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-137, actions=[DestroyAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=19))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-137, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)])])])
```

### Moving wooden planks from the crafting grid to the existing wooden plank in the hotbar's first slot, then discarding that plank:

```
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-557, actions=[CraftCreativeAction(creativeItemNetworkId=1), CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:planks, runtimeId=5, componentBased=false), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=10430], usingNetId=false, netId=0)], timesCrafted=1), TakeAction(count=64, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-557), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-557, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=64, stackNetworkId=8, customName=, durabilityCorrection=0)])])])
[SERVER BOUND] - ItemStackRequestPacket(requests=[ItemStackRequest(requestId=-559, actions=[DropAction(count=64, source=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=8), randomly=false)], filterStrings=[], textProcessingEventOrigin=null)])
[CLIENT BOUND] - ItemStackResponsePacket(entries=[ItemStackResponse(result=OK, requestId=-559, containers=[ItemStackResponseContainer(container=CURSOR, items=[ItemStackResponseSlot(slot=0, hotbarSlot=0, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)])])])
```