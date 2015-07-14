##############################################
##一般数据,一般来说不需要更改
##############################################
#cpp 目录
GameDir=(
"../Classes"
)

#固定的cpp文件，比如一个文件夹100个cpp文件中只有10个需要，可以写死在这里
NormalCppfile=(

)

#头文件目录
NormalIncludefile=(
"\$(LOCAL_PATH)/../../Classes\\"
"\$(LOCAL_PATH)/../../Classes/SDKManager"
)

#不需要的文件（在GameDir中）
NormalExcludefile=(
"../Classes/SDKManager/NS_SDKPlatform.cpp"

)
