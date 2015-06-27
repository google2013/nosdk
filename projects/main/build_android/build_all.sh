#全局统一配置，针对一键编译
ALL="1"

#通用宏设置
PLATFORMHONG=(
#"DEBUG"     ##编译模式
#"NO_SHARE"  ##微信分享
#"TEST_APP" ##app测试
)

##
source ./tools/platform_path.sh
source platform_input.sh $*

##app
source ./platform/build_noplatform.sh "$ISREFRESHMK_SET" "$ISBUILD_SET" "$ISCOPYRESOURCE_SET" "$ISCOPYICON_SET" "$ISPACKAGE_SET"

##xx
#source ./platform/build_xx.sh "$ISREFRESHMK_SET" "$ISBUILD_SET" "$ISCOPYRESOURCE_SET" "$ISCOPYICON_SET" "$ISPACKAGE_SET"