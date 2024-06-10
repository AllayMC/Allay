---
comments: true
---

## Crafting a cookie:

### C -> S:

```
ItemStackRequestPacket(
    requests=[
        ItemStackRequest(
            requestId=-43, 
            actions=[
                CraftRecipeAction(recipeNetworkId=230), 
                CraftResultsDeprecatedAction(resultItems=[BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:cookie, runtimeId=271, componentBased=false), damage=0, count=8, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=0], usingNetId=false, netId=0)], timesCrafted=1), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=35, stackNetworkId=44)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=36, stackNetworkId=46)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=37, stackNetworkId=45)), 
                TakeAction(count=8, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-43), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0))
            ], 
            filterStrings=[], 
            textProcessingEventOrigin=null)
    ]
)
```

### S -> C:

```
ItemStackResponsePacket(
    entries=[
        ItemStackResponse(
            result=OK,
            requestId=-43, 
            containers=[
                ItemStackResponseContainer(
                    container=CRAFTING_INPUT, 
                    items=[
                        ItemStackResponseSlot(
                            slot=35, hotbarSlot=35, count=0, 
                            stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(
                            slot=36, hotbarSlot=36, count=0, 
                            stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(
                            slot=37, hotbarSlot=37, count=0, 
                            stackNetworkId=0, customName=, durabilityCorrection=0)]
                    ), 
                ItemStackResponseContainer(
                    container=CURSOR, 
                    items=[
                        ItemStackResponseSlot(
                            slot=0, hotbarSlot=0, count=8, 
                            stackNetworkId=47, customName=, durabilityCorrection=0)]
                )
            ]
        )
    ]
)
```

## Crafting a cake:

### C -> S:

```
ItemStackRequestPacket(
    requests=[
        ItemStackRequest(
            requestId=-1405, 
            actions=[
                CraftRecipeAction(recipeNetworkId=176), 
                CraftResultsDeprecatedAction(
                    resultItems=[
                        BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:cake, runtimeId=419, componentBased=true), damage=0, count=1, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=0], usingNetId=false, netId=0), 
                        BaseItemData(definition=SimpleItemDefinition(identifier=minecraft:bucket, runtimeId=362, componentBased=false), damage=0, count=3, tag=null, canPlace=[], canBreak=[], blockingTicks=0, blockDefinition=UnknownDefinition[runtimeId=0], usingNetId=false, netId=0)
                    ], 
                    timesCrafted=1), 
                ConsumeAction(
                    count=1, 
                    source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=32, stackNetworkId=18)), 
                ConsumeAction(
                    count=1, 
                    source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=33, stackNetworkId=17)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=34, stackNetworkId=16)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=35, stackNetworkId=20)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=36, stackNetworkId=23)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=37, stackNetworkId=21)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=38, stackNetworkId=25)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=39, stackNetworkId=26)), 
                ConsumeAction(count=1, source=ItemStackRequestSlotData(container=CRAFTING_INPUT, slot=40, stackNetworkId=27)), 
                CreateAction(slot=0), 
                TakeAction(count=1, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-1405), destination=ItemStackRequestSlotData(container=CURSOR, slot=0, stackNetworkId=0)), 
                CreateAction(slot=1), 
                PlaceAction(count=3, source=ItemStackRequestSlotData(container=CREATED_OUTPUT, slot=50, stackNetworkId=-1405), destination=ItemStackRequestSlotData(container=HOTBAR_AND_INVENTORY, slot=2, stackNetworkId=13))], 
            filterStrings=[], 
            textProcessingEventOrigin=null
        )
    ]
)
```

### S -> C:

```
ItemStackResponsePacket(
    entries=[
        ItemStackResponse(
            result=OK, 
            requestId=-1405, 
            containers=[
                ItemStackResponseContainer(
                    container=CRAFTING_INPUT, 
                    items=[
                        ItemStackResponseSlot(slot=32, hotbarSlot=32, count=0, stackNetworkId=0, customName=, durabilityCorrection=0),
                        ItemStackResponseSlot(slot=33, hotbarSlot=33, count=0, stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(slot=34, hotbarSlot=34, count=0, stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(slot=35, hotbarSlot=35, count=0, stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(slot=36, hotbarSlot=36, count=0, stackNetworkId=0, customName=, durabilityCorrection=0),
                        ItemStackResponseSlot(slot=37, hotbarSlot=37, count=0, stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(slot=38, hotbarSlot=38, count=0, stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(slot=39, hotbarSlot=39, count=0, stackNetworkId=0, customName=, durabilityCorrection=0), 
                        ItemStackResponseSlot(slot=40, hotbarSlot=40, count=0, stackNetworkId=0, customName=, durabilityCorrection=0)
                    ]
                ), 
                ItemStackResponseContainer(
                    container=CURSOR, 
                    items=[
                        ItemStackResponseSlot(slot=0, hotbarSlot=0, count=1, stackNetworkId=28, customName=, durabilityCorrection=0)
                    ]
                ), 
                ItemStackResponseContainer(
                    container=HOTBAR_AND_INVENTORY, 
                    items=[
                        ItemStackResponseSlot(slot=2, hotbarSlot=2, count=6, stackNetworkId=13, customName=, durabilityCorrection=0)
                    ]
                )
            ]
        )
    ]
)
```