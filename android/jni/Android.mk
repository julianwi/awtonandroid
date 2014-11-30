LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := awtpeer
LOCAL_SRC_FILES := awtpeer.c
LOCAL_LDLIBS    := -landroid -llog

include $(BUILD_SHARED_LIBRARY)