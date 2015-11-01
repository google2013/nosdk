#######################################################
##!!!!!此脚本会更新android.mk，
#######################################################
AndroidVersion="1.3.5"
PlatformFile="android.mk"
PlatformDir="./jni"
PlatformTarget="libcocos2dcpp"
PlatformName="PLATFORM_MI"
PlatformGameName="NAME_SGZ15"

##############################################
##平台数据,需要更改
##############################################

#数组遍历
# for data in ${array[@]}
# do
#     echo ${data}
# done
#cppfile,附加平台需要的cpp文件
PlatformCppfile=(
"../../Classes/MISDK/TK_MIManager.cpp\\"
)

#include dir,附加平台需要的头文件目录
PlatformIncludefile=(
"\$(LOCAL_PATH)/../../Classes/MISDK\\"
)

#excludefile , Game中不需要的类
PlatformExcludefile=(

)

PlatformOtherDefine=(
"LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static cocosdenshion_static cocos_extension_static"
"#LOCAL_LDLIBS += \$(LOCAL_PATH)/libzyc.a"
"include \$(BUILD_SHARED_LIBRARY)"
"\$(call import-module,CocosDenshion/android) \\"
"\$(call import-module,cocos2dx) \\"
"\$(call import-module,extensions) \\"
"\$(call import-module,cocos2dx/platform/third_party/android/prebuilt/libcurl) \\"
"\$(call import-module,cocos2dx/platform/third_party/android/prebuilt/libxml2)"
)

##############################################
##一般数据,一般来说不需要更改
##############################################
#cpp 目录
GameDir=(
"../Classes/Game"
)

#cpp文件
NormalCppfile=(
"../../Classes/AppDelegate.cpp\\"
"../../Classes/Common/aescrypt/TK_Aescrypt.cpp\\"
"../../Classes/Common/ByteOrder.cpp\\"
"../../Classes/Common/CCControlButtonWithFX/CCControlButtonWithFX.cpp\\"
"../../Classes/Common/CCControlButtonWithFX/CCControlButtonWithFXLoader.cpp\\"
"../../Classes/Common/CCLabelFX/CCLabelFX.cpp\\"
"../../Classes/Common/CodeHelper/CodeGenerator.cpp\\"
"../../Classes/Common/JavaCppHelper.cpp\\"
"../../Classes/Common/LogSystem.cpp\\"
"../../Classes/Common/OCandCppHelperAndroid.cpp\\"
"../../Classes/Common/ParamListParser.cpp\\"
"../../Classes/Common/Queue.cpp\\"
"../../Classes/Common/RbfParser.cpp\\"
"../../Classes/Common/RichBox.cpp\\"
"../../Classes/Common/SerBuffer.cpp\\"
"../../Classes/Common/SocketClient.cpp\\"
"../../Classes/Common/SoundDelegate.cpp\\"
"../../Classes/Common/StringConvert.cpp\\"
"../../Classes/Common/TK_DirtyWordsCheck.cpp\\"
"../../Classes/Common/TK_TableCell.cpp\\"
"../../Classes/Common/TK_XmlReader.cpp\\"
"../../Classes/Common/VersionControl.cpp\\"
"../../Classes/jsoncpp/src/lib_json/json_reader.cpp\\"
"../../Classes/jsoncpp/src/lib_json/json_value.cpp\\"
"../../Classes/jsoncpp/src/lib_json/json_writer.cpp\\"
"../../Classes/WeChatSdk/TK_WeChatManager.cpp\\"
"../../Classes/SinaWeibo/TK_LayerWeiboBand.cpp\\"
"../../Classes/SinaWeibo/TK_LayerWeiboShare.cpp\\"
"../../Classes/SinaWeibo/WeiboManagerAndroid.cpp\\"
"../../Classes/iap/PaymentViewandroid.cpp\\"
"../../Classes/Common/md5.c\\"
)

#头文件目录
NormalIncludefile=(
"\$(LOCAL_PATH)/../../Classes\\"
"\$(LOCAL_PATH)/../../Classes\\"
"\$(LOCAL_PATH)/../../Classes/Common\\"
"\$(LOCAL_PATH)/../../Classes/Common/aescrypt\\"
"\$(LOCAL_PATH)/../../Classes/Common/CCControlButtonWithFX\\"
"\$(LOCAL_PATH)/../../Classes/Common/CCLabelFX\\"
"\$(LOCAL_PATH)/../../Classes/Common/CodeHelper\\"
"\$(LOCAL_PATH)/../../Classes/Game\\"
"\$(LOCAL_PATH)/../../Classes/iap\\"
"\$(LOCAL_PATH)/../../Classes/jsoncpp\\"
"\$(LOCAL_PATH)/../../Classes/jsoncpp/include\\"
"\$(LOCAL_PATH)/../../Classes/jsoncpp/include/json\\"
"\$(LOCAL_PATH)/../../Classes/jsoncpp/src\\"
"\$(LOCAL_PATH)/../../Classes/jsoncpp/src/lib_json\\"
"\$(LOCAL_PATH)/../../Classes/SinaWeibo\\"
"\$(LOCAL_PATH)/../../Classes/WeChatSdk\\"
"\${NDKROOT}/sources/cxx-stl/stlport/stlport"
)

#不需要的文件（在GameDir中）
NormalExcludefile=(
"TK_LayerSetting.cpp"
"TK_LayerWeiboSetting.cpp"
)

#遍历文件夹 输出cpp文件名
list_dir(){
    for file in $1/*
    do
        if [ -d $file ]; then
            echo $file
            list_dir $file
        elif [ -f $file ]; then
            echo $file
            if [ ${file##*.} == "cpp" ] || [ ${file##*.} == "c" ]; then
                canDo="false"
                for data in ${NormalExcludefile[@]}
                do
                    if [ $data == `basename $file` ]; then
                        canDo="true"
                    fi
                done
                if [ $canDo == "false" ]; then
                    echo "../$file \\" >> "$2"
                fi
            fi
        fi
    done
}

Name="$PlatformName$AndroidVersion.mk"
if [ -f "$Name" ]; then
    rm -r "$Name"
fi

echo "LOCAL_PATH := \$(call my-dir)" > "$Name"
echo "" >> "$Name"
echo "include \$(CLEAR_VARS)" >> "$Name"
echo "" >> "$Name"
echo "LOCAL_CFLAGS += -D$PlatformGameName=1" >> "$Name"
echo "LOCAL_CFLAGS += -D$PlatformName=1" >> "$Name"
echo "#LOCAL_CFLAGS += -DTEST_APP=1" >> "$Name"
echo "LOCAL_CFLAGS += -DDEBUG=1" >> "$Name"
echo "LOCAL_MODULE := game_shared" >> "$Name"
echo "" >> "$Name"
echo "LOCAL_MODULE_FILENAME := $PlatformTarget" >> "$Name"
echo "" >> "$Name"
echo "" >> "$Name"
echo "LOCAL_SRC_FILES := hellocpp/main.cpp \\" >> "$Name"
echo "libiconv.a \\" >> "$Name"
#Game中的cpp文件

for data in ${PlatformCppfile[@]}
do
echo ${data} >> "$Name"
done

#其它文件夹文件
for data in ${NormalCppfile[@]}
do
    echo ${data} >> "$Name"
done

for data in ${GameDir[@]}
do
    list_dir $data $Name
done

echo "" >> "$Name"
echo "LOCAL_C_INCLUDES := \$(LOCAL_PATH) \\" >> "$Name"

for data in ${PlatformIncludefile[@]}
do
    echo ${data} >> "$Name"
done

for data in ${NormalIncludefile[@]}
do
    echo ${data} >> "$Name"
done

IFS=";"

for data in ${PlatformOtherDefine[@]}
do
echo ${data} >> "$Name"
done

mv "$Name" "${PlatformDir}/Android.mk"