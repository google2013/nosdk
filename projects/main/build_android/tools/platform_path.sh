if [ "$ALL" != "1" ] && [ "$ALL" != "0" ]; then
	str=`pwd`
	echo ${str%/*}
	serchPath=${str%/*}
else
	echo `pwd`
	serchPath=`pwd`
	# echo "tes"
fi
#脚本搜索路径--即build_android路径
#serchPath="/Users/crimoon/Documents/project_3/trunk/clientTK/cocos2dx222/projects/ProjectTK/build_android"

PATH=$PATH:"$serchPath":"$serchPath/tools":"$serchPath/platform";
#打包出来的路径
out_path=~/Desktop/apk
#key文件在的路径
key_path="/Users/crimoon/Documents/project_2/Project_TK/tools/androidpacker/"

export PATH