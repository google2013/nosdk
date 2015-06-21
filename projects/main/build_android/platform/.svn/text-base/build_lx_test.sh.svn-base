##############################################
##平台数据,需要更改
##############################################

SDKDIR="sgz_lx"
BUILDDIR="build_android"
TOOLSDIR="tools"
PlatformName="PLATFORM_LX"
PlatformGameName=(
"NAME_SGZ15"
)
PlatformTarget="libcocos2dcpp"

#cppfile,附加平台需要的cpp文件
PlatformCppfile=(
"../../Classes/LXSDK/TK_LXManager.cpp\\"
)

#include dir,附加平台需要的头文件目录
PlatformIncludefile=(
"\$(LOCAL_PATH)/../../Classes/LXSDK\\"
)

for a in $*
do
    if [ "$a" == "mk" ]; then
        ISREFRESHMK_SET="1"
        ISREFRESHMK_PAM="mk"
    fi

    if [ "$a" == "build" ]; then
        ISBUILD_SET="1"
        ISBUILD_PAM="build"
    fi

    if [ "$a" == "res" ]; then
        ISCOPYRESOURCE_SET="1"
        ISCOPYRESOURCE_PAM="res"
    fi
done


if [ "$ALL" != "1" ] && [ "$ALL" != "0" ]; then
    source ../tools/platform_path.sh
    ##使用自定义设置
    PLATFORMHONE_CHILD=(
    #    "DEBUG"         ##编译模式
    #    "NO_SHARE"      ##微信分享
    #"TEST_APP"     ##app测试
    )
fi
source platform_param.sh