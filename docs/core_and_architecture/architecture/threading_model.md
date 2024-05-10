---
comments: true
---

## Threading models ##

### Preface

Before going into more depth, it is essential to understand Allay's threading model and the design motivation behind it.

This not only avoids unintended concurrency problems, but also improves plugin performance to a certain extent

### World

Unlike Nukkit, in Allay, each world runs on a separate thread.

Please note that world in Allay cannot be simply understood as level in Nukkit. A world consists of multiple Dimensions and must contain a Main World Dimension, with the Nether and End World Dimensions being optional.

Unlike Nukkit, each world has a **separate** event bus (and task scheduler). This means that if you want to listen to events generated in a world, you need to register listeners to that world's event bus.

### Server instances

The server instance runs on the main thread. After completing the startup task, the main thread goes to method Server::start() and blocks in this method until the server shuts down.

Similar to the world, the server instance also has tick (see Server::tick()). the rate of tick is the same as for the world, 20 times per second. Under normal circumstances, server ticks have so few tasks that they can be ignored.
The server instance also has a **separate** event bus and task scheduler. Events not related to the world will be handled on the server instance's event bus (e.g. players entering and leaving the server).

### Packet handling

#### A few concepts

Netty thread: the thread that directly calls the org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler::handlePacket() method, this thread is related to the underlying RakNet

Network thread: the world holds networkThread, which is a virtual thread

#### Packet processing flow

When the server receives a packet, it first tries to call PacketProcessor::handleAsync() directly on the Netty thread. This method has two return values: HANDLED and UNHANDLED.

If handleAsync() returns HANDLED, the package has been processed and no further steps will be taken.

If handleAsync() returns UNHANDLED, the packet has not been processed. Next, the packet will be added to the world's synchronized packetQueue.

**The network thread and the world thread of its corresponding world run mutually exclusive**, which means that at the same moment only one thread is running while the other is dormant. We use CAS for this purpose, if you are curious about the implementation details, you can go through the source code, which will not be covered here.

The network thread is blocked in a dead loop, which is theoretically much faster than 20 times per second without affecting performance (because it is running on a virtual thread). Each time it loops the network thread looks at the list of synchronized packets, and if it is not empty it calls PacketProcessor::handleSync() to process the packet. This method has no return value, this is the final stop for the packet.

#### Design Motivation

The biggest reason for this design is that we want to be able to process a packet as fast as possible, which is of great importance in PVP, mini game suits.

In Nukkit, all packets are processed in the main thread. The main thread tick rate is 20 times per second, which means that the server doesn't process received packets immediately, and in the worst case needs to wait 50ms.

This can simply be interpreted as higher player latency, as evidenced by placing or destroying blocks/attacking players or creatures with more noticeable lag. This doesn't cause too much of an issue in survival play servers, but it can be very problematic in mini-game play servers.