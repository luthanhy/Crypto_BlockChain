import java.util.*;

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
    public static List<Block> AddNewBlock(List<Block> blockList){
        Block nBlock = new Block();
        if (blockList == null){
            blockList = new ArrayList<>();
            nBlock.setBlockId("0");
            nBlock.setPrevHash(null);
            blockList.add(nBlock);
        }
        else{
            Block data = blockList.get(blockList.size()-1);
            Long id = Long.parseLong(data.getBlockId())+1L;
            nBlock.setBlockId(id.toString());
            nBlock.setPrevHash(data.getHashBlock());
            blockList.add(nBlock);
        }
        return blockList;
    }
    public static Block getCurrentBlock(List<Block> blockList){
        if(blockList == null){
            return  null;
        }
        if(blockList.isEmpty()){
            return null;
        }
        return blockList.get(blockList.size()-1);
    }
    public static List<Block> CloneBlockList(List<Block> blockList){
        List<Block> kq = new ArrayList<>();
        for(Block i: blockList){
            kq.add(new Block(i));
        }
        return kq;
    }

    private static String hashStrOfBlockList(List<Block> blockList){
        if(blockList == null){
            return null;
        }
        if(blockList.isEmpty()){
            return null;
        }
        String result = HashFunction.SHA_512(blockList.get(0).getHashBlock(), "");
        for(int i =1; i< blockList.size(); i++){
            if(blockList.get(i).getPrevHash().compareTo(blockList.get(i-1).getHashBlock())==0){
                result = HashFunction.SHA_512(result+blockList.get(i).getHashBlock()+blockList.get(i).getPrevHash(), blockList.get(i).getPrevHash());
            }
            else{
                return null;
            }
        }
        return result;
    }
    public static void ShowBlockList(List<Block> blockList){
        System.out.println("\t+++ BlockList has ("+blockList.size()+") blocks!");
        System.out.println("-".repeat(40));
        for(Block i: blockList){
            System.out.println(i);
            System.out.println("-".repeat(40));
        }
    }
    public static List<Block> TrueBlockList(List<List<Block>> allBlockList){
        int numberBlockList = allBlockList.size();
        int minimumNumberListToAcceptListBlock = numberBlockList/2+1;
        Dictionary<String, Integer[]> dictionary = new Hashtable<>();
        String hashBlockList;
        Integer[] value;
        int index = 0;
        for(List<Block> i : allBlockList){
            hashBlockList = hashStrOfBlockList(i);
            if(hashBlockList == null){
                index+=1;
                continue;
            }
            value = dictionary.get(hashBlockList);
            if(value != null){
                if(value[1]+1 >= minimumNumberListToAcceptListBlock){
//                    Enumeration<String> k = dictionary.keys();
//                    while (k.hasMoreElements()) {
//                        String key = k.nextElement();
//                        System.out.println("Key: " + key + ", Value: "
//                                + dictionary.get(key)[0]+"-"+dictionary.get(key)[1]);
//                    }
                    return allBlockList.get(index);
                }
                dictionary.put(hashBlockList, new Integer[]{value[0], value[1]+1});
            }
            else{
                dictionary.put(hashBlockList, new Integer[]{index, 1});
            }
            index+=1;
        }
        return null;
    }
}
