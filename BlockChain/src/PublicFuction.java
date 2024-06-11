public class PublicFuction {
    public static Boolean isEmptyStr(String s){
        return s==null || s.isEmpty();
    }
    public static String getStrOrDefault(String s, String defaultStr){
        if(isEmptyStr(s)){
            return defaultStr;
        }
        return s;
    }
    public static String getStrOrDefault(String s){
        if(isEmptyStr(s)){
            return "NULL";
        }
        return s;
    }
    public static <T> T getValueOrDefault(T e, T defaultValue){
        if(e == null){
            return defaultValue;
        }
        return e;
    }
    public static String getNCharInStr(String s, int nStart, int nStop){
        if(nStart+nStop >= s.length()){
            return s;
        }
        String kq = "";
        kq+= s.substring(0, nStart);
        kq += "...";
        kq+= s.substring(s.length()-nStop);
        return kq;
    }
}
