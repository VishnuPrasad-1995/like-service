package com.mavericsystems.likeservice.constant;

public final class LikeConstant {

    private LikeConstant() {
        // restrict instantiation
    }
    public static final String LIKENOTFOUNDFORPOSTORCOMMENT = "Like not found for post or comment id : ";
    public static final String FEIGNEXCEPTON = "User service unavailable";
    public static final String POSTIDMISMATCH = "Id passed in url and request body does not match";
    public static final String LIKEID = " & Like id :";
}
