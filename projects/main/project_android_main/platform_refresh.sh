PlatformDir="./jni"
Name="Android.mk"
if [ -f "$Name" ]; then
rm -r "$Name"
fi

source platform_define.sh
source normal_define.sh

echo "LOCAL_PATH := \$(call my-dir)" > "$Name"
echo "" >> "$Name"
echo "include \$(CLEAR_VARS)" >> "$Name"
echo "" >> "$Name"

for data in ${PLATFORMHONE_CHILD[@]}
do
echo "LOCAL_CFLAGS += -D${data}=1" >> "$Name"
done

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
source file_list.sh $data $Name
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

###有些宏需要链接库
for data in ${PLATFORMHONE_CHILD[@]}
do
if [ "$data" == "TEST_APP" ]; then
echo "LOCAL_LDLIBS += \$(LOCAL_PATH)/libzyc.a" >> "$Name"
fi
done

mv "$Name" "${PlatformDir}/Android.mk"