import java.util.List;

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
    public static Long getBalanceFromBlockDetails(List<BlockDetail> blockDetailList, String id){
        Long kq = 0L;
        for(BlockDetail i: blockDetailList){
            if(i.getToAccount().compareTo(id)==0){
                kq += i.getAmount();
            }
            else if(i.getFromAccount().compareTo(id)==0){
                kq -= i.getAmount();
            }
        }
        return kq;
    }
    public static Long getBalanceFromBlocks(List<Block> blockList, String id){
        Long kq = 0L;
        for(Block i: blockList){
            kq += getBalanceFromBlockDetails(i.getBlockDetailList(), id);
        }
        return kq;
    }
}
