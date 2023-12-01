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