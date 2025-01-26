---
comments: true
---

# Chunk Freezing Issue

Chunk freezing has been an issue for a long time, and we have been looking for the root cause and solutions. Below is a
summary of the current progress.

## Overview of the Issue

Players occasionally experience certain chunks not loading while exploring, specifically where problematic chunks appear
transparent but have collision boxes. Nearby chunks are also likely to be affected and exhibit similar symptoms.
Additionally, once the chunk freezing occurs, the client is likely to encounter a series of other issues, including but
not limited to the inability to properly send and receive certain network packets.

## Initial Investigation

We conducted a thorough investigation, ruling out the possibility that the server failed to send the chunks
successfully, indicating that the issue is most likely client-side. Noticing that this issue rarely, if ever, occurs in
BDS, we focused on the chunk sending sequence and conducted a series of experiments controlling variables, with the
results as follows:

- Fewer chunk packets sent per gt results in less chunk freezing (significant impact).
- SYNC mode is less prone to chunk freezing than ASYNC mode.
- Enabling the sub-chunk sending system slightly increases the likelihood of chunk freezing.
- The larger the render distance, the more likely chunk freezing occurs.

Initially, we suspected that the issue might be due to the incorrect use of the NetworkChunkPublishUpdatePacket (NCP).
We tried various adjustments, including changing the sending order of NCP and LevelChunkPacket (LCP), and even
attempting to send an NCP every gt (inspired by df-mc). Unfortunately, these attempts did not yield significant results,
only slightly reducing the occurrence of the issue.

## Is It LevelChunkPacket's Fault?

Cool_Loong later pointed out that there might be issues with Allay's chunk packet encoding, and we indeed found two
issues:

- Incorrect biome encoding.
- Incorrect handling logic for empty sections.

After fixing these two issues, we were pleased to notice a significant improvement in chunk freezing, and it even
disappeared for a while. However, time proved that the issue was not completely resolved, as we still occasionally
noticed the problem, especially when teleporting to a new world, where some chunks from the old world would persist and
require a re-login to fix.

## Timing

We analyzed the various symptoms of this issue again and noticed two key points:

- The client's network thread partially or completely chunks when the issue occurs.
- The lower the chunk packet sending rate, the less likely the issue occurs.

We wondered if the issue might be because the **client processed a large number of chunk packets in a short period**,
causing some packets to fail to decode properly. Given that chunk packets are complex, this hypothesis seemed plausible.
Subsequent tests further validated this idea.

Based on the above hypothesis, we attempted to explain the various phenomena of this issue:

### Why Does Client Network Thread Freeze During Chunk Freezing?

As hypothesized, the client has issues parsing some chunk packets, throwing exceptions in the network thread, preventing
normal logic from running and ultimately causing the network thread to freeze.

### Why Sync Chunk Sending Less Prone to Chunk Freezing than Async?

With synchronous chunk sending, the sequence is relatively fixed, so the sending rate should be stable. However, with
asynchronous sending, a large number of chunks (or two chunk packets almost simultaneously) might be sent at once,
causing a very high instant chunk sending rate, meeting the conditions for triggering chunk freezing.

### Why Does Enabling the SubChunk Sending System Slightly Increase the Likelihood of Chunk Freezing?

The sub-chunk sending system sends a large number of sub-chunk packets (mass packet sending).

## Preliminary Conclusion

Both incorrect chunk packets and excessively high chunk packet sending rates can cause chunk freezing. Based on the
above understanding, limiting the sending rate of chunk packets is an effective method, provided that the chunk packet
encoding is correct. By lowering the value of `world-settings.chunk-try-send-count-per-tick`, we found that the chunk
freezing issue almost disappeared.

## 2024/5/31 Supplement

It seems that the incorrect order of chunk sending can also cause chunk freezing. Chunks close to the player **must** be
sent first. If a distant chunk is sent while skipping an unsent closer chunk, the skipped chunk is very likely to
freeze.

This indicates that asynchronous chunk sending cannot simply be made asynchronous. The best approach is to create a
separate chunk sending thread to ensure the correct order of chunk sending.
