#全局统一配置，针对一键编译
ALL="1"
#是否需要刷新Android.mk，新添加文件后推荐设置为1
ISREFRESHMK_SET="1"
#通用宏设置
PLATFORMHONG=(
#"DEBUG"     ##编译模式
#"NO_SHARE"  ##微信分享
#"TEST_APP" ##app测试
)

##
source ./tools/build_platform.sh
source ./tools/normal_define.sh
source ./tools/platform_define.sh
source ./tools/file_list.sh

##中手游SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_zz...\033[0m"
source ./platform/build_zz.sh
echo -e "\033[31;1mbuild_zz finished \033[0m"
echo "-------------------------------------------------"
echo ""

##91SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_91...\033[0m"
source ./platform/build_91.sh
echo -e "\033[31;1mbuild_91 finished \033[0m"
echo "-------------------------------------------------"
echo ""

##360SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_360...\033[0m"
source ./platform/build_360.sh
echo -e "\033[31;1mbuild_360 finished \033[0m"
echo "-------------------------------------------------"
echo ""

##应用宝SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_ac...\033[0m"
source ./platform/build_ac.sh
echo -e "\033[31;1mbuild_ac finished \033[0m"
echo "-------------------------------------------------"
echo ""

##安智SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_anzhi...\033[0m"
source ./platform/build_anzhi.sh
echo -e "\033[31;1mbuild_anzhi finished \033[0m"
echo "-------------------------------------------------"
echo ""

##拜见主公SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_bjzg...\033[0m"
source ./platform/build_bjzg.sh
echo -e "\033[31;1mbuild_bjzg finished \033[0m"
echo "-------------------------------------------------"
echo ""

##当乐SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_dl...\033[0m"
source ./platform/build_dl.sh
echo -e "\033[31;1mbuild_dl finished \033[0m"
echo "-------------------------------------------------"
echo ""

##多酷SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_duoku...\033[0m"
source ./platform/build_duoku.sh
echo -e "\033[31;1mbuild_duoku finished \033[0m"
echo "-------------------------------------------------"
echo ""

##机锋SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_gfan...\033[0m"
source ./platform/build_gfan.sh
echo -e "\033[31;1mbuild_gfan finished \033[0m"
echo "-------------------------------------------------"
echo ""

##华为SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_hw...\033[0m"
source ./platform/build_hw.sh
echo -e "\033[31;1mbuild_hw finished \033[0m"
echo "-------------------------------------------------"
echo ""

##联想SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_lx...\033[0m"
source ./platform/build_lx.sh
echo -e "\033[31;1mbuild_lx finished \033[0m"
echo "-------------------------------------------------"
echo ""

##小米SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_mi...\033[0m"
source ./platform/build_mi.sh
echo -e "\033[31;1mbuild_mi finished \033[0m"
echo "-------------------------------------------------"
echo ""

##msccSDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_mscc...\033[0m"
source ./platform/build_mscc.sh
echo -e "\033[31;1mbuild_mscc finished \033[0m"
echo "-------------------------------------------------"
echo ""

##oppoSDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_oppo...\033[0m"
source ./platform/build_oppo.sh
echo -e "\033[31;1mbuild_oppo finished \033[0m"
echo "-------------------------------------------------"
echo ""

##偶玩SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_ow...\033[0m"
source ./platform/build_ow.sh
echo -e "\033[31;1mbuild_ow finished \033[0m"
echo "-------------------------------------------------"
echo ""

##搜狗SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_sg...\033[0m"
source ./platform/build_sg.sh
echo -e "\033[31;1mbuild_sg finished \033[0m"
echo "-------------------------------------------------"
echo ""

##ucSDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_uc...\033[0m"
source ./platform/build_uc.sh
echo -e "\033[31;1mbuild_uc finished \033[0m"
echo "-------------------------------------------------"
echo ""

##步步高SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_vivo...\033[0m"
source ./platform/build_vivo.sh
echo -e "\033[31;1mbuild_vivo finished \033[0m"
echo "-------------------------------------------------"
echo ""

##豌豆夹SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_wdj...\033[0m"
source ./platform/build_wdj.sh
echo -e "\033[31;1mbuild_wdj finished \033[0m"
echo "-------------------------------------------------"
echo ""

##wszgSDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_wszg...\033[0m"
source ./platform/build_wszg.sh
echo -e "\033[31;1mbuild_wszg finished \033[0m"
echo "-------------------------------------------------"
echo ""

##微游戏SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_wyx...\033[0m"
source ./platform/build_wyx.sh
echo -e "\033[31;1mbuild_wyx finished \033[0m"
echo "-------------------------------------------------"
echo ""

##拜见公主SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_xz...\033[0m"
source ./platform/build_xz.sh
echo -e "\033[31;1mbuild_xz finished \033[0m"
echo "-------------------------------------------------"
echo ""

##应用宝SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_yyb...\033[0m"
source ./platform/build_yyb.sh
echo -e "\033[31;1mbuild_yyb finished \033[0m"
echo "-------------------------------------------------"
echo ""

##主公快逃SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_zgkt...\033[0m"
source ./platform/build_zgkt.sh
echo -e "\033[31;1mbuild_zgkt finished \033[0m"
echo "-------------------------------------------------"
echo ""

##卓然SDK
echo "-------------------------------------------------"
echo -e "\033[31;1mbuild platform --build_zr...\033[0m"
source ./platform/build_zr.sh
echo -e "\033[31;1mbuild_zr finished \033[0m"
echo "-------------------------------------------------"
echo ""

##中手游SDK
#echo "build platform --build_zz..."
#source ./platform/build_zz.sh