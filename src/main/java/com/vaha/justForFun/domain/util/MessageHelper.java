package com.vaha.justForFun.domain.util;

import com.vaha.justForFun.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "none";
    }
}