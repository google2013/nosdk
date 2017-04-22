LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS += -DNAME_MYGAME=1 -DANDROID=1
LOCAL_MODULE := game_shared

LOCAL_MODULE_FILENAME := libsample

LOCAL_LDLIBS    := -lm -llog -landroid

LOCAL_SRC_FILES := ../../sdkmanager/JavaHelper.cpp \
../../sdkmanager/SDKManager.cpp \
../../ui/AndroidClasses.cpp \
../../ui/Main.cpp \

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
$(LOCAL_PATH)/../../sdkmanager \
$(LOCAL_PATH)/../../ui \
$(LOCAL_PATH)/../.. \

LOCAL_STATIC_LIBRARIES := itoa-dropins itoa-jnipp

include $(BUILD_SHARED_LIBRARY)

# Files in 'common' use itoa-dropins.
$(call import-module,itoa-dropins/android)
$(call import-module,itoa-jnipp/android)