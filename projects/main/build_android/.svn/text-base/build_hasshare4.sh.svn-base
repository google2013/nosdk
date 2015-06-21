#全局统一配置，针对一键编译
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
#"NO_SHARE"  ##微信分享
#"TEST_APP" ##app测试
)

##
source ./tools/platform_path.sh

##熊猫玩 武汉游戏群
source ./platform/build_wh.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"


##酷动游戏
source ./platform/build_kd.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##91SDK 1
#source ./platform/build_91.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##汇智SDK 3
# source ./platform/build_huizhi.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##起点SDK 1
source ./platform/build_qidian.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##起点SDK 1
source ./platform/build_pkpk.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##起点SDK 1
source ./platform/build_dx.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##起点SDK 1
source ./platform/build_mm.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

# ##起点SDK 1
# source ./platform/build_wdj_special.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

# ##起点SDK 1
# source ./platform/build_wdj.sh "$ISREFRESHMK_PAM" "$ISBUILD_PAM" "$ISCOPYRESOURCE_PAM" "$ISPACKAGE_PAM"

##中手游SDK
#echo "build platform --build_zz..."
#source ./platform/build_zz.sh