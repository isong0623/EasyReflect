package com.dreaming.easy.reflect;

import android.app.Application;

import com.dreaming.easy.lib.reflect.ReflectClass;

/**
 * use guide:
 *
 * this class implements part method for android.app.AndroidThread.java
 *
 * shows how to get the current Application
 *
 * you just call new AndroidThread().getApplication()
 *
 */
public class AndroidThread extends ReflectClass {
    private static final String ANDROID_THREAD_CLASS_NAME = "android.app.ActivityThread";
    public static class CompanionObject extends ReflectClass{
        public CompanionObject() {
            super(
                    new Builder()
                            //public static final String TAG = "ActivityThread";
                            .putFieldSafe(ANDROID_THREAD_CLASS_NAME, "TAG")
                            // public static ActivityThread currentActivityThread()
                            .putMethodSafe(ANDROID_THREAD_CLASS_NAME, "currentActivityThread")
                            .build()
            );
        }
        public String getTag(){
            return getSafe("TAG");
        }
        public Object currentActivityThread(){
            return invokeSafe("currentActivityThread");
        }
    }
    private static CompanionObject companionObject = new CompanionObject();
    public static Object currentActivityThread(){
        return companionObject.currentActivityThread();
    }
    public AndroidThread(){
        super(
                new Builder()
                        //public Application getApplication()
                        .putMethodSafe(ANDROID_THREAD_CLASS_NAME, currentActivityThread(), "getApplication")
                        .build()
        );
    }
    public Application getApplication(){
        return invokeSafe("getApplication");
    }
}