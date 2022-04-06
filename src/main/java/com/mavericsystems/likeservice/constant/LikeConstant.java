package com.mavericsystems.likeservice.constant;

public final class LikeConstant {

    private LikeConstant() {
        // restrict instantiation
    }
    public static final String LIKENOTFOUND = "Like not found for : ";
    public static final String FEIGNEXCEPTON = "User service unavailable";
    public static final String POSTIDMISMATCH = "Id passed in url and request body does not match";
}
