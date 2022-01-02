package org.jefferies.queue.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Rank {
    private String id;
    private String display;
    private int priority;
    private String permission;
}
