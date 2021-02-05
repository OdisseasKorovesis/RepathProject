package com.odkor.repathproject.utils;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {


    @Test
    public void getExceptionStackTrace_Success() {

        String ACTUAL = "";

        try {
            throw new Exception("This is an exception");
        } catch (Exception ex) {
            ACTUAL = Utils.getExceptionStackTrace(ex);
        }

        ACTUAL = ACTUAL.replaceAll(System.lineSeparator(), "\n");

        System.out.println(ACTUAL);
        String EXPECTED = "java.lang.Exception: This is an exception\n" +
                "\tat com.odkor.repathproject.utils.UtilsTest.getExceptionStackTrace_Success(UtilsTest.java:17)\n" +
                "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n" +
                "\tat org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)\n" +
                "\tat org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)\n" +
                "\tat org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)\n" +
                "\tat org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)\n" +
                "\tat org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)\n" +
                "\tat org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)\n" +
                "\tat org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)\n" +
                "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)\n" +
                "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)\n" +
                "\tat org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)\n" +
                "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)\n" +
                "\tat org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)\n" +
                "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:413)\n" +
                "\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n" +
                "\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n" +
                "\tat com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)\n" +
                "\tat com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)\n" +
                "\tat com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)\n";

        assertEquals(EXPECTED, ACTUAL);
    }
}
