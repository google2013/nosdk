LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS += -DNAME_MYGAME=1
LOCAL_MODULE := game_shared

LOCAL_MODULE_FILENAME := libcocos2dcpp


LOCAL_SRC_FILES := hellocpp/main.cpp \
libiconv.a \
../../Classes/AppDelegate.cpp \
../../Classes/Common/JavaHelper.cpp \
../../Classes/GameOverScene.cpp \
../../Classes/HelloWorldScene.cpp \
../../Classes/LoginBackScene.cpp \
../../Classes/PayBackScene.cpp \
../../Classes/SDKManager/NS_SDKFactory.cpp \
../../Classes/SDKManager/NS_SDKManager.cpp \
../../Classes/SDKManager/NS_SDKPlatformAndroid.cpp \
../../Classes/SettingScene.cpp \

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
$(LOCAL_PATH)/../../Classes\
$(LOCAL_PATH)/../../Classes/SDKManager
LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static cocosdenshion_static cocos_extension_static
include $(BUILD_SHARED_LIBRARY)
$(call import-module,CocosDenshion/android) \
$(call import-module,extensions) \
$(call import-module,cocos2dx)
