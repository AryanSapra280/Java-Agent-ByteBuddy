package com.agent;

import net.bytebuddy.asm.Advice;

/**
 * This class is to define the delegate methods definition.
*/
public class ReplayInterceptor {


    /**
     * This method is the interceptor of save method in the spring boot application.
    */
//    @Advice.OnMethodEnter
//    public static String interceptHello() {
//        System.out.println("hello save method intercepted");
//        return "intercepted hello";
//    }

    @Advice.OnMethodEnter
    public static Post interceptPost(Post post) {
        return post;
    }
}

