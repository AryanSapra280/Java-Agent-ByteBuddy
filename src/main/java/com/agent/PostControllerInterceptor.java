package com.agent;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
//import org.springframework.http.ResponseEntity;


public class PostControllerInterceptor {

//    private static Logger logger = LoggerFactory.getLogger(PostControllerInterceptor.class);

//    @RuntimeType
//    public ResponseEntity<?> savePostInterceptorOnEny() {
//
//    }
@RuntimeType
public static String intercept(@Origin Method method, @AllArguments Object[] args,
                               @SuperCall Callable<?> superMethod) throws Throwable {
    // Interceptor logic before method execution
    System.out.println("Interceptor: Before method execution: " + method.getName());

    // You can access and manipulate method arguments here
    String postString = (String) args[0];
    System.out.println("Request body: " + postString);

    // Proceed with the original method execution
    String response = (String)superMethod.call();

    // Interceptor logic after method execution
    System.out.println("Interceptor: After method execution: " + method.getName());

    return response;
}

    @Advice.OnMethodExit
    public static void onExit(@Advice.Return String responseEntity) {
        System.out.println("Exiting method savePost with response entity: " + responseEntity);
    }
}
