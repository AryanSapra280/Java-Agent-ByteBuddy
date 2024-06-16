package com.agent;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import static net.bytebuddy.implementation.MethodDelegation.to;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;

/**
 * This class is responsible for creating java agent.
*/
public final class Agent {

    private Agent() {
        super();
    }

    /**
     * This method is the entry point of java agent.
    */
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Premain method executed");
        System.out.println("ByteBuddy's classloader: " + Agent.class.getClassLoader());
        final String MODE = System.getenv("HT_MODE");
//        new AgentBuilder.Default()
//                .type((ElementMatchers.nameStartsWith("com.application.mainApplication.PostService")))
//                .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
//                    String name = typeDescription.getName();
//                    if (name.startsWith("com.application")) {
//                        System.out.println("Transforming class: " + name + ", ClassLoader: " + classLoader);
//                    }
//                    return builder.method(ElementMatchers.hasMethodName("hello")
//                                    .or(ElementMatchers.hasMethodName("savePost")))
//                            .intercept(MethodDelegation.to(PostRepoInterceptor.class));
//                })
//                .with(new AgentBuilder.Listener.Adapter() {
//                    @Override
//                    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
//                        System.out.println("Transformed: " + typeDescription.getName());
//                    }
//
//                    @Override
//                    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
//                        System.err.println("Error transforming " + typeName);
//                        throwable.printStackTrace();
//                    }
//                })
//                .installOn(instrumentation);
        if("RECORD".equals(MODE)) {
            new AgentBuilder.Default()
                    .type((ElementMatchers.nameStartsWith("com.application.mainApplication.controllers")))
                    .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
                        String name = typeDescription.getName();
                        if (name.startsWith("com.application.controllers")) {
                            System.out.println("Transforming class: " + name + ", ClassLoader: " + classLoader);
                        }
                        return builder.method(ElementMatchers.named("savePost"))
                                .intercept(MethodDelegation.to(RecordInterceptor.class));
                    })
                    .with(new AgentBuilder.Listener.Adapter() {
                        @Override
                        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                            System.out.println("Transformed: " + typeDescription.getName());
                        }

                        @Override
                        public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
                            System.err.println("Error transforming " + typeName);
                            throwable.printStackTrace();
                        }
                    })
                    .installOn(instrumentation);

        }
    }
}
