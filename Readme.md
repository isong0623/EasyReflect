# EasyReflect
简易反射

# 导入依赖

```bash
    //https://github.com/isong0623/EasyReflect
    implementation 'com.github.isong0623:EasyReflect:1.0'
```
# 主要类
包名：com.dreaming.easy.lib.reflect
以下函数实现比较简单，不作过多解释。
## ReflectField<P,T>
反射成员，包含静态成员及类成员，内有安全方法getSafe不抛出异常以及抛出异常的get方法

## ReflectMethod<P,T>
反射方法，包含安全方法invokeSafe不抛出异常，以及invoke抛出异常

## ReflectClass 
抽象反射类，使用Builder模式创建方法和成员，初始化后该类持有所有方法和成员的映射，提供相应的invoke和get方法

# Demo
见app内com.dreaming.easy.reflect.AndroidThread.java

```java
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
```
