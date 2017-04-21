LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS += -DNAME_MYGAME=1
LOCAL_MODULE := game_shared

LOCAL_MODULE_FILENAME := libsample


LOCAL_SRC_FILES := ../../JavaHelper.cpp \
../../SDKManager.cpp \

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
$(LOCAL_PATH)/../..
include $(BUILD_SHARED_LIBRARY)