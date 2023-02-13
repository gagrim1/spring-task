package com.romanov.spring_task.model.enumurated;

import lombok.Getter;

@Getter
public enum Content {
    UPVOTE((short) 1, "UPVOTE"),
    DOWNVOTE((short) 2, "DOWNVOTE");

    private final Short id;
    private final String name;

    Content(Short id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Content getById(Short id) {
        return Content.values()[id];
    }
}
