# nomal_opts_act()
# {
ISBUILD_SET="0"
ISREFRESHMK_SET="0"      
ISCOPYRESOURCE_SET="0"       
ISCOPYICON_SET="0"        
ISPACKAGE_SET="0"         

    echo -e "\n### nomal_opts_act ###\n"

    while [ -n "$1" ]
    do
    case "$1" in 
        -a)
            # echo "Found the -a option"
            ISBUILD_SET="1"
            ISREFRESHMK_SET="1"
            ISCOPYRESOURCE_SET="1"
            ISCOPYICON_SET="1"
            ISPACKAGE_SET="1"
            ;;
        -b)
            # echo "Found the -b option"
            # echo "The parameter follow -b is $2" 
            ISBUILD_SET="1"
            if [ "$2" == "mk" ]; then
                ISREFRESHMK_SET="1"
            fi
            shift
            ;;
        -c)
            # echo "Found the -c option"
            # echo "The parameter follow -c is $2"
            ISCOPYRESOURCE_SET="1"
            if [ "$2" == "icon" ]; then
                ISCOPYICON_SET="1"
            fi
            shift
            ;;
        -p)
            ISPACKAGE_SET="1"
            ;;
         *)
             echo "$1 is not an option"
            ;;
    esac
    shift
    done
# }

