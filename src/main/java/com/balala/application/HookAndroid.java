/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.balala.application;

import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.telephony.TelephonyManager;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by GCTang on 2019/01/14.
 * Description:
 */

public class HookAndroid implements IXposedHookLoadPackage {

    private String hookPkg = "com.test";//需要hook的包名
    private final String TAG = "HOOK_OK!!! ";
    private final int TAG_INT = 666;

    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals(hookPkg))
            return;

        //开始啦~hook
        /////////////////////////hook SystemProperties//////////////////////////////////////////////
        findAndHookMethod("android.os.SystemProperties",loadPackageParam.classLoader,"get",String.class,new XC_MethodHook(){
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if("gsm.operator.alpha".equals(param.args[0])){
                    param.setResult(TAG);
                }else if("gsm.operator.numeric".equals(param.args[0])){
                    param.setResult(TAG);
                }else if("gsm.sim.operator.numeric".equals(param.args[0])){
                    param.setResult(TAG);
                }else if("gsm.sim.state".equals(param.args[0])){
                    param.setResult(TAG);
                }
            }
        });
        /////////////////////////hook TelephonyManager//////////////////////////////////////////////
        findAndHookMethod(TelephonyManager.class.getName(),loadPackageParam.classLoader,"getDeviceId",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        findAndHookMethod(TelephonyManager.class.getName(),loadPackageParam.classLoader,"getSubscriberId",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        Class<?> mTelephonyManager = XposedHelpers.findClass("android.telephony.TelephonyManager",loadPackageParam.classLoader);
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getSimState",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG_INT);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getDeviceSoftwareVersion",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getLine1Number",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getNetworkOperator",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getNetworkOperatorName",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getPhoneType",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG_INT);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getNetworkType",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG_INT);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getSimSerialNumber",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        XposedHelpers.findAndHookMethod(mTelephonyManager,"getSimOperator",new XC_MethodHook(){//getSimState

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });

        /////////////////////////hook WifiInfo//////////////////////////////////////////////
        XposedBridge.hookAllMethods(android.net.wifi.WifiInfo.class,"getIpAddress",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(TAG_INT);
            }

        });
        XposedBridge.hookAllMethods(android.net.wifi.WifiInfo.class,"getMacAddress",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(TAG);
            }

        });
        XposedBridge.hookAllMethods(android.net.wifi.WifiInfo.class,"getBSSID",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(TAG);

            }

        });
        XposedBridge.hookAllMethods(android.net.wifi.WifiInfo.class,"getSSID",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(TAG);

            }

        });
        ///////////////////////////hook Secure//////////////////////////////////////////
        XposedBridge.hookAllMethods(android.provider.Settings.Secure.class,"getString",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (param.args != null && param.args.length > 1 && param.args[1] != null && param.args[1].equals("android_id")) {
                    param.setResult(TAG);
                }

            }

        });

        ///////////////////////////Hook BluetoothDevice////////////////////////////////////////
        findAndHookMethod(BluetoothDevice.class.getName(),loadPackageParam.classLoader,"getName",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });
        findAndHookMethod(BluetoothDevice.class.getName(),loadPackageParam.classLoader,"getAddress",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(TAG);
            }

        });

        /////////////////////////hook Build//////////////////////////////////////////////
        try{

            XposedHelpers.findField(Build.VERSION.class, "INCREMENTAL").set(null, TAG);
            XposedHelpers.findField(Build.VERSION.class, "RELEASE").set(null, TAG);
            XposedHelpers.findField(Build.VERSION.class, "SDK").set(null, TAG);
            XposedHelpers.findField(Build.VERSION.class, "CODENAME").set(null, TAG);
            XposedHelpers.findField(Build.class, "BOOTLOADER").set(null, TAG);
            XposedHelpers.findField(Build.class, "PRODUCT").set(null, TAG);
            XposedHelpers.findField(Build.class, "HOST").set(null, TAG);
            XposedHelpers.findField(Build.class, "TAGS").set(null, TAG);
            XposedHelpers.findField(Build.class, "TYPE").set(null, TAG);
            XposedHelpers.findField(Build.class, "MANUFACTURER").set(null, "oppo");//TAG  test 模拟oppo手机
            XposedHelpers.findField(Build.class, "HARDWARE").set(null, TAG);
            XposedHelpers.findField(Build.class, "RADIO").set(null, TAG);
            XposedHelpers.findField(Build.class, "SERIAL").set(null, TAG);
            XposedHelpers.findField(Build.class, "FINGERPRINT").set(null, TAG);
            XposedHelpers.findField(Build.class, "TIME").set(null, TAG_INT);

        }catch (Exception e){

        }
        XposedHelpers.setStaticObjectField(android.os.Build.class, "BOARD", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "DEVICE", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI2", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "BRAND", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "ID", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "DISPLAY", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "USER", TAG);
        XposedHelpers.setStaticObjectField(android.os.Build.class, "DEVICE", TAG);

        ///////////////////////////Hook Display//////////////////////////////////////////
        XposedBridge.hookAllMethods(android.view.Display.class,"getWidth",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(TAG_INT);
            }

        });
        XposedBridge.hookAllMethods(android.view.Display.class,"getHeight",new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(TAG_INT);
            }

        });

        ///////////////////////////Hook DisplayMetrics////////////////////////////////////
        /*Class CompatibilityInfo = XposedHelpers.findClass("android.content.res.CompatibilityInfo", loadPackageParam.classLoader);
        XposedHelpers.findAndHookMethod(Resources.class, "updateConfiguration", Configuration.class, DisplayMetrics.class, CompatibilityInfo, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("KK_HOOK Resources come on");
                DisplayMetrics displayMetrics;

                if(param.args[1] != null) {
                    displayMetrics = new DisplayMetrics();
                    displayMetrics.setTo((DisplayMetrics) param.args[1]);
                    param.args[1] = displayMetrics;
                } else {
                    displayMetrics = ((Resources)param.thisObject).getDisplayMetrics();
                }
                XposedBridge.log("KK_HOOK Resources w:"+displayMetrics.widthPixels);
                XposedBridge.log("KK_HOOK Resources h:"+displayMetrics.heightPixels);
                *//*if(param.args[1] != null) {
                    XposedHelpers.setIntField(param.args[1],"widthPixels",TAG_INT);
                    XposedHelpers.setIntField(param.args[1],"heightPixels",TAG_INT);
                }*//*
            }
        });*/


        ///////////////////////////end//////////////////////////////////////////


    }
}
