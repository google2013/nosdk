cd /Users/crimoon0071/Documents/Project_GHZ/Project/Trunk/client_CR/GHZ/cocos2dx222/projects/ProjectGHZ/build_android
#版本号--针对出包后的名字
version="150"
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
#是否debug
ISPACKAGE_DEBUG="0"
#是否signed
ISPACKAGE_SIGNED="1"


#是否需要刷新Android.mk，新添加文件后推荐设置为1
ISREFRESHMK_PAM=""
#是否需要编译
ISBUILD_PAM=""
#是否需要拷贝资源
ISCOPYRESOURCE_PAM=""
#是否打包
ISPACKAGE_PAM=""
#是否debug
ISPACKAGE_DEBUG_PAM=""

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

if [ "$a" == "debug" ]; then
ISPACKAGE_DEBUG="1"
ISPACKAGE_DEBUG_PAM="debug"
fi

if [ "$a" == "unsign" ]; then
ISPACKAGE_SIGNED="0"
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
if [ "$ISPACKAGE_DEBUG" == "1" ]; then
	PLATFORMHONG=(
	"DEBUG"     ##编译模式
	"NO_SHARE"  ##微信分享
	#"TEST_APP" ##app测试
	)
else
	PLATFORMHONG=(
	#"DEBUG"     ##编译模式
	"NO_SHARE"  ##微信分享
	#"TEST_APP" ##app测试
	)
fi



##路径读取
source ./tools/platform_path.sh

##zzsdk 
#source ./platform/build_zz.sh "$ISPACKAGE_PAM"

##91SDK 
#source ./platform/build_91.sh "$ISPACKAGE_PAM"

##360SDK 
source ./platform/build_360.sh 

##acSDK 
source ./platform/build_ac.sh 

##anzhiSDK 
source ./platform/build_anzhi.sh 

##dlSDK 
source ./platform/build_dl.sh 


