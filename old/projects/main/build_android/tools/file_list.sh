#遍历文件夹 输出cpp文件名

    for file in $1/*
    do
        if [ -d $file ]; then
            echo $file
            source file_list.sh $file $2
        elif [ -f $file ]; then
            echo $file
            if [ ${file##*.} == "cpp" ] || [ ${file##*.} == "c" ]; then
                canDo="false"
                for data in ${NormalExcludefile[@]}
                do
                    if [ $data == $file ]; then
                        canDo="true"
                    fi
                done
                if [ $canDo == "false" ]; then
                    echo "../$file \\" >> "$2"
                fi
            fi
        fi
    done