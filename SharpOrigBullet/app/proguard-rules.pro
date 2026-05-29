##############################
## FIX RELEASE CRASH (JNI + Activities)
##############################

## Keep Application class
-keep class com.sharp.pro.app.App { *; }

## Keep all Activities (your app uses reflection + JNI)
-keep class com.sharp.pro.*Activity { *; }

## Keep Services
-keep class com.sharp.pro.FloatingActivity { *; }
-keep class com.sharp.pro.Overlay { *; }

## Keep anything JNI might load
-keep class com.sharp.pro.** { *; }

## Don’t obfuscate methods called from native code
-keepclassmembers class * {
    native <methods>;
}

##############################
## FreeReflection / BlackReflection
##############################
-keep class me.weishu.reflection.** { *; }
-keep class com.github.tiann.** { *; }
-keep class com.github.CodingGay.** { *; }

##############################
## Prevent removal of your JNI library loader
##############################
-keep class * {
    public static void loadLibrary(java.lang.String);
    public static void loadLibraries(...);
}

##############################
## Prevent crash from removing constructors
##############################
-keepclassmembers class * {
    public <init>(...);
}
