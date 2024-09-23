---
comments: true
---

NOTICE: The following content is answered by @daoge_cmd and does not represent the opinions of others.

Time: 2024/4/4

## Is this server based on Nukkit?

No. It is not based on PowerNukkitX. Unlike PowerNukkitX, this project is completely rewritten, it is not based on Nukkit.

## What are the advantages of this server?

The README already mentioned some advantages, but I think the most important ones are code quality and performance.

Allay was writing unit tests at the same time as he was writing the content, which proved to be very useful and prevented the creation of many hidden bugs more than a few times.

There are also a number of interesting design patterns that we used to design the architecture, most notably the component system, which requires a separate page.

## Why did you start this project?

The reasons are complex, but the main reason is that by mid-2023 I personally believe that PNX will have reached a stage where it will become unmaintainable.

Because PNX has to be backward compatible, and it already has a bunch of features, it takes a lot of time to debug every update, and even every update has some bugs that can't be fixed immediately.
(However, we are glad to see that PNX has now abandoned backward compatibility, which makes PNX2 much more maintainable.)

PNX has also exposed a lot of performance problems in the actual operation of some large servers, and a large part of the problems are related to the underlying layer, which can't be simply optimized away.

For example, we found that PNX has a large memory footprint in the case of 10000+ blocks, and after dumping down memory, we found that 60% of the footprint is due to duplicate BlockState objects.
By tracing the object creation, we found that this is because the block does not reuse the same block state, and a new one is created every time it is used. The same problem occurs with Block objects.

So in Allay, we applied the hedonic pattern to BlockState and the singleton pattern to Block, which made allay's memory usage and gc frequency in high load situations reduced dramatically.

## Is a third party server (Allay, PNX) better or BDS?

It's pointless to compare use cases.

For example, if I want to run a survival server, I would definitely prefer BDS + LL.
But if I'm running a small game server, I'd consider PNX or some other 3rd party.

I'm not a big fan of this comparison, in fact I don't like the idea of comparing or even competing with each other. If you look closely enough, you can see that Allay relies on LL for their data export.
Even our organization has LL devs. LL is an excellent project, PNX is also a good project, hhh.

## Will Allay keep writing?

That's a hard question to answer.

MCBE's projects are interest-driven. Maybe at some point in the future I will lose my interest in MCBE.

## About the future of PowerNukkitX?

We will not stop working on PowerNukkitX immediately.

Even though Allay is architecturally superior to PNX, PNX is still the most feature-rich third-party server project out there. We've added a lot of features to PNX over time.
PNX has been refactored to be much more maintainable, and even some of the allay-exclusive optimizations (including BlockState) have been ported to PNX.

However, we can't guarantee that we'll be able to maintain PNX forever, as only Cool_Loong is actively maintaining PNX at the moment.
If there are no new active contributors, we may have to stop updating PNX at some point in the future.

But no matter what, I think PNX has left a colorful mark on the history of MCBE.
The biggest significance of PNX is that it has raised the technical level of the whole Chinese Nukkit community by more than one level, and brought in a lot of fresh blood.
It also indirectly spawned the birth of Nukkit-MOT and other excellent branches.
I can't say how well PNX is written, but if PNX hadn't been born, it would have been Nukkit-PM1E monopolizing the community unilaterally, not to mention [this PR](https://github.com/CloudburstMC/Nukkit/pull/2175).