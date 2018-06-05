package com.lj.autoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "AutoTest";
    private static final String APP = "com.tencent.mm";
    private UiDevice mDevice;
    private Context mContext;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        mContext = InstrumentationRegistry.getTargetContext();
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        startAutoEvent();
    }

    private void startAutoEvent() {
        try {
            mDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mDevice.pressHome();
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(APP);
        mContext.startActivity(intent);
        cycleAddFriend();
    }

    private void cycleAddFriend() {
        try {
            while (!isWeixinHome()) {
                mDevice.pressBack();
            }
            UiObject addButton = mDevice.findObject(new UiSelector().description("更多功能按钮"));
            addButton.clickAndWaitForNewWindow();
            UiObject addObject = mDevice.findObject(new UiSelector().text("添加朋友"));
            addObject.clickAndWaitForNewWindow();
            UiObject textObject = mDevice.findObject(new UiSelector().text("微信号/QQ号/手机号"));
            textObject.clickAndWaitForNewWindow();
            UiObject editObject = mDevice.findObject(new UiSelector().className("android.widget.EditText"));
            editObject.setText("1111");
            UiObject resultObject = mDevice.findObject(new UiSelector().textContains("搜索"));
            resultObject.clickAndWaitForNewWindow();
            UiObject resultAddObject = mDevice.findObject(new UiSelector().text("添加到通讯录"));
            resultAddObject.clickAndWaitForNewWindow();
            UiObject messageObject = mDevice.findObject(new UiSelector().textContains("你需要发送")
                    .fromParent(new UiSelector().className("android.widget.EditText")));
            messageObject.setText("111");
            UiObject sendObject = mDevice.findObject(new UiSelector().text("发送"));
            sendObject.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean isWeixinHome() {
        while (!mDevice.getCurrentPackageName().equals("com.tencent.mm")) {

        }
        String activity;
        try {
            activity = mDevice.executeShellCommand("dumpsys activity activities");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity = activity.substring(activity.indexOf("mResumedActivity"),
                        activity.indexOf("mResumedActivity") + 80);
            } else {
                activity = activity.substring(activity.indexOf("mFocusedActivity"),
                        activity.indexOf("mFocusedActivity") + 80);
            }
            if (activity.contains("com.tencent.mm/.ui.LauncherUI")) {
                Log.d(TAG, "current activity is : " + activity);
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
