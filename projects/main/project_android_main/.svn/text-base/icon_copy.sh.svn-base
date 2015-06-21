ProjectName=`basename "$1"`

if [ "${ProjectName}" == "sgz15_wyx" ] || [ "${ProjectName}" == "sgz15_qla" ] || [ "${ProjectName}" == "sgz15_mz" ] || [ "${ProjectName}" == "sgz15_pkpk" ] || [ "${ProjectName}" == "sgz15_qidian" ] || [ "${ProjectName}" == "sgz15_dx" ] || [ "${ProjectName}" == "sgz15_lt" ]; then
    cp "../build_android/res/normal1/Icon-36.png" res/drawable-ldpi/icon.png
    cp "../build_android/res/normal1/Icon-48.png" res/drawable-mdpi/icon.png
    cp "../build_android/res/normal1/Icon-72.png" res/drawable-hdpi/icon.png
    cp "../build_android/res/normal1/Icon-96.png" res/drawable-xhdpi/icon.png
elif [ "${ProjectName}" == "sgz15_360" ] || [ "${ProjectName}" == "sgz15_wdj" ] || [ "${ProjectName}" == "sgz15_vivo" ] || [ "${ProjectName}" == "sgz15_yyb" ] || [ "${ProjectName}" == "sgz15_oppo" ] || [ "${ProjectName}" == "sgz_lx" ] || [ "${ProjectName}" == "sgz15_kd" ] || [ "${ProjectName}" == "sgz15_mm" ]; then
    cp "../build_android/res/normal2/Icon-36.png" res/drawable-ldpi/icon.png
    cp "../build_android/res/normal2/Icon-48.png" res/drawable-mdpi/icon.png
    cp "../build_android/res/normal2/Icon-72.png" res/drawable-hdpi/icon.png
    cp "../build_android/res/normal2/Icon-96.png" res/drawable-xhdpi/icon.png
elif [ "${ProjectName}" == "sgz15" ] || [ "${ProjectName}" == "sgz15_mi" ] || [ "${ProjectName}" == "sgz15_hw" ]; then
    cp "../build_android/res/normal3/Icon-36.png" res/drawable-ldpi/icon.png
    cp "../build_android/res/normal3/Icon-48.png" res/drawable-mdpi/icon.png
    cp "../build_android/res/normal3/Icon-72.png" res/drawable-hdpi/icon.png
    cp "../build_android/res/normal3/Icon-96.png" res/drawable-xhdpi/icon.png
elif [ -d "../build_android/res/${ProjectName}" ]; then
	echo "../build_android/res/${ProjectName}"
    cp "../build_android/res/${ProjectName}/Icon-36.png" res/drawable-ldpi/icon.png
    cp "../build_android/res/${ProjectName}/Icon-48.png" res/drawable-mdpi/icon.png
    cp "../build_android/res/${ProjectName}/Icon-72.png" res/drawable-hdpi/icon.png
    cp "../build_android/res/${ProjectName}/Icon-96.png" res/drawable-xhdpi/icon.png
else
    cp "../build_android/res/normal/Icon-36.png" res/drawable-ldpi/icon.png
    cp "../build_android/res/normal/Icon-48.png" res/drawable-mdpi/icon.png
    cp "../build_android/res/normal/Icon-72.png" res/drawable-hdpi/icon.png
    cp "../build_android/res/normal/Icon-96.png" res/drawable-xhdpi/icon.png
fi


##删除无用文件

