# OA智能签章框架Java版

政企(信产)研发中心 Android开源版基础UI库

## 联系方式

使用存在问题可联系研发中心或发邮件至zealpeng@163.com邮箱反馈。。。

## 版本说明
### 最新版本

[![](https://www.jitpack.io/v/ZealP/Android_BenchmarkUI.svg)](https://github.com/ZealP/Android_BenchmarkUI)

<!-- ### Demo下载
[![downloads](https://img.shields.io/badge/downloads-430k-blue.svg)](https://github.com/zhou-you/RxEasyHttp/blob/master/RxEasyHttp-Demo.apk?raw=true) -->

### 添加Gradle依赖

1. 先在项目根目录的 build.gradle 的 repositories 添加:

```gradle
allprojects {
        repositories {
            ...
            maven { url 'https://www.jitpack.io' }
        }
    }
```

2. 然后在dependencies添加:

```gradle
dependencies {
    implementation 'com.github.ZealP:SmartSignet_OA_Java:最新版本号'
}
```
### 全局配置

一般在 Aplication，或者基类中，只需要调用一次即可。
初始化需要Application#onCreate()中初始化，记得在manifest.xml中注册Application。

Application:

```java
public class App extends Application {
    public static App application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        /**
         * 初始化UI组件
         * @param application Application对象
         */
        BenchmarkUI.init(application);
    }
}
```

## 调用实例

功能调用可参照demo代码:

>声明 ：具体的调用方法正在加紧编写中,敬请期待

## 声明

此框架不得用来进行非本司或未被本司授权的商业化开发

想使用Kotlin版本的框架请移步 [OA智能签章框架Kotlin版(已停止维护)](https://github.com/ZealP/SmartSignet_OA_Kotlin)

