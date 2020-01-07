package Login;

//字符串工具类
public class StringUtil {

    public static boolean isEmpty(String str)
    {
        if(str==null||"".equals(str.trim())) {
            return true;
        }
        else {
            return false;
        }
    }
    @SuppressWarnings("null")
    public static boolean isNotEmpty(String str)
    {
        if(str!=null&&!"".equals(str.trim())) {
            return true;
        }
        else {
            return false;
        }
    }
}


