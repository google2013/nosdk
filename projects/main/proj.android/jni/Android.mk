LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS += -DDEBUG=1
LOCAL_CFLAGS += -DNAME_SGZ15=1
LOCAL_MODULE := game_shared

LOCAL_MODULE_FILENAME := libcocos2dcpp


LOCAL_SRC_FILES := hellocpp/main.cpp \
libiconv.a \
../../Classes/AppDelegate.cpp \
../../Classes/GameOverScene.cpp \
../../Classes/HelloWorldScene.cpp \
../../Classes/SDKManager/NS_SDKFactory.cpp \
../../Classes/SDKManager/NS_SDKManager.cpp \
../../Classes/SDKManager/NS_SDKPlatformAndroid.cpp \

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
$(LOCAL_PATH)/../../Classes\
$(LOCAL_PATH)/../../Classes/SDKManager
LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static cocosdenshion_static
include $(BUILD_SHARED_LIBRARY)
$(call import-module,CocosDenshion/android) \
$(call import-module,cocos2dx)
