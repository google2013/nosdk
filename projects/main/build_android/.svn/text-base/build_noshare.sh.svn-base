cd#全局统一配置，针对一键编译
ALL="1"
#是否需要刷新Android.mk，新添加文件后推荐设置为1
ISREFRESHMK_SET="0"
#是否需要编译
ISBUILD_SET="0"
#是否需要拷贝资源
ISCOPYRESOURCE_SET="0"
#是否需要打包
ISPACKAGE_SET="0"

#是否需要刷新Android.mk，新添加文件后推荐设置为1
ISREFRESHMK_PAM=""
#是否需要编译
ISBUILD_PAM=""
#是否需要拷贝资源
ISCOPYRESOURCE_PAM=""
#是否打包
ISPACKAGE_PAM=""

#输入参数判断
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
# "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM"

#通用宏设置
PLATFORMHONG=(
    #"DEBUG"     ##编译模式
    "NO_SHARE"  ##微信分享
    #"TEST_APP" ##app测试
)

##
source ./tools/platform_path.sh

##奇天乐地
source ./platform/build_qla.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##奇天乐地  拜见公主
source ./platform/build_qla_bjzg.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##奇天乐地  放过那貂蝉
source ./platform/build_qla_thqpz.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##搜狗SDK 2
source ./platform/build_sg.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##奇天乐地
source ./platform/build_jodo.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"
## PS: 卓动的需要资源分包,resources-iphonehd中按排名pic_1.plist以后的资源全部分出去
###################################################################################################

##多酷SDK 2
source ./platform/build_duoku.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

#卓然SDK 真三国无双7 6
source ./platform/build_91_zgkt.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

###################################################################################################

#卓然SDK 三国志2014 5   中手游
source ./platform/build_sgz2014.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##卓然SDK 放开那貂蝉 7   中手游
source ./platform/build_fkndc.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

#博雅科诺 3     中手游
source ./platform/build_bgkn.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##4399 1    中手游
source ./platform/build_4399.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##奇天乐地  拜见公主   pps
source ./platform/build_pps.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"



###################################################################################################

##卓然SDK 真三国无双7 4
source ./platform/build_zsgws7_zr.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##奇天乐地  放过那貂蝉
source ./platform/build_bf.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##卓然SDK fgndc
##中手游SDK
#echo "build platform --build_zz..."
#source ./platform/build_zz.sh