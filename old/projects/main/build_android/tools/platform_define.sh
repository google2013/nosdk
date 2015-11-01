
#注意： '/' 是转义字符需要转义的主要有$,
PlatformOtherDefine=(
"LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static cocosdenshion_static cocos_extension_static"
            
"include \$(BUILD_SHARED_LIBRARY)"

"\$(call import-module,CocosDenshion/android) \\"
"\$(call import-module,extensions) \\"
"\$(call import-module,cocos2dx)"
)