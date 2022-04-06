package com.mavericsystems.likeservice.constant;

public final class LikeConstant {

    private LikeConstant() {
        // restrict instantiation
    }

    public static final String LIKE_NOT_FOUND_FOR_POST_OR_COMMENT = "Like not found for post or comment id : ";
    public static final String FEIGN_EXCEPTION = "User service unavailable";
    public static final String POST_ID_MISMATCH = "Id passed in url and request body does not match";
    public static final String LIKE_ID = " & Like id :";
}
