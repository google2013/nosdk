##############################################
##平台数据,需要更改
##############################################
PLATFORMHONE_CHILD=()
SDKDIR="sgz15_ac"
BUILDDIR="build_android"
TOOLSDIR="tools"
PlatformTarget="libcocos2dcpp"
PlatformName="PLATFORM_AC"
PlatformGameName=(
"NAME_SGZ15"
)

EXINCLUDE_SRC=(
"ZZSDKManager.java"
"ProjectTKApp.java"
"CommonSdkManager.java"
)

#cppfile,附加平台需要的cpp文件
PlatformCppfile=(
# "../../Classes/ACSDK/TK_ACManager.cpp\\"
)

#include dir,附加平台需要的头文件目录
PlatformIncludefile=(
# "\$(LOCAL_PATH)/../../Classes/ACSDK\\"
)

if [ "$ALL" != "1" ] && [ "$ALL" != "0" ]; then
    source ../tools/platform_path.sh
	ISREFRESHMK_SET="0"
	ISBUILD_SET="0"
	ISCOPYRESOURCE_SET="0"
    ##使用自定义设置
    PLATFORMHONE_CHILD=(
    #    "DEBUG"         ##编译模式
    # "NO_SHARE"      ##微信分享
    #"TEST_APP"     ##app测试
    )
fi

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

    if [ "$a" == "package" ]; then
        ISPACKAGE_SET="1"
        ISPACKAGE_PAM="package"
    fi

    #默认的mk build res package signed 流程
    if [ "$a" == "buildall" ]; then
        ISREFRESHMK_SET="1"
        ISREFRESHMK_PAM="mk"
        ISBUILD_SET="1"
        ISBUILD_PAM="build"
        ISCOPYRESOURCE_SET="1"
        ISCOPYRESOURCE_PAM="res"
        ISPACKAGE_SET="1"
        ISPACKAGE_PAM="package"
    fi

done

source platform_param.sh