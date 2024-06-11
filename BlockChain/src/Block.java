import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Block {
    private String blockId;
    private String hashBlock;
    private String prevHash;
    private List<BlockDetail> blockDetailList;
    public Block(){

    }
    public Block(Block block){
        this.blockId = block.getBlockId();
        this.hashBlock = block.getHashBlock();
        this.prevHash = block.getPrevHash();
        this.blockDetailList = new ArrayList<>(block.getBlockDetailList());
    }
    public void AddBlockDetail(BlockDetail blockDetail){
        if(blockDetail == null){
            return;
        }
        else if(blockDetail.getAmount()==null || blockDetail.getFromAccount()==null || blockDetail.getToAccount()==null || blockDetail.getSignature()==null){
            return;
        }
        if(blockDetailList == null){
            blockDetailList = new ArrayList<>();
        }
        blockDetailList.add(blockDetail);
    }
    public void HashBlock(){
        if(blockId == null){
            hashBlock = "NULL";
            return;
        }
        StringBuilder hashMessage = new StringBuilder();
        hashMessage.append(PublicFuction.getStrOrDefault(blockId));
        if(blockDetailList != null){
            if(blockDetailList.isEmpty()){
                hashMessage.append(blockDetailList.hashCode());
            }
            else{
                List<BlockDetail> temp = new ArrayList<>(List.copyOf(blockDetailList));
                temp.sort((a,b) -> (new BigInteger(HashFunction.SHA_512(a.getAmount().toString()+a.getFromAccount()+a.getToAccount()+a.getSignature(), ""), 16)
                        .subtract(new BigInteger(HashFunction.SHA_512(b.getAmount().toString()+b.getFromAccount()+b.getToAccount()+b.getSignature(), ""), 16))).hashCode());
                for (BlockDetail i: blockDetailList) {
                    hashMessage.append(PublicFuction.getValueOrDefault(i.getAmount(), 0));
                    hashMessage.append("-").append(PublicFuction.getStrOrDefault(i.getFromAccount()));
                    hashMessage.append("-").append(PublicFuction.getStrOrDefault(i.getToAccount()));
                    hashMessage.append("-").append(PublicFuction.getStrOrDefault(i.getSignature()));
                }
            }
        }
        hashBlock = HashFunction.SHA_512(hashMessage.toString(), HashFunction.SHA_256(blockId, ""));
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getHashBlock() {
        HashBlock();
        return hashBlock;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public List<BlockDetail> getBlockDetailList() {
        return blockDetailList;
    }

    public void setBlockDetailList(List<BlockDetail> blockDetailList) {
        this.blockDetailList = blockDetailList;
    }
    @Override
    public String toString(){
        HashBlock();
        StringBuilder kq = new StringBuilder();
        kq.append("BlockId: ").append(PublicFuction.getStrOrDefault(blockId));
        kq.append("\nHashBlock: ").append(PublicFuction.getStrOrDefault(hashBlock));
        kq.append("\nPreviousHash: ").append(PublicFuction.getStrOrDefault(prevHash));
        if(blockDetailList == null){
            kq.append("\nBlockDetailList: NULL");
        }
        else{
            kq.append("\nBlockDetailList: ");
            String a,b,c;
            for(BlockDetail i : blockDetailList){
                a= PublicFuction.getStrOrDefault(i.getFromAccount());
                b=PublicFuction.getStrOrDefault(i.getToAccount());
                c = PublicFuction.getStrOrDefault(i.getSignature());
                a = PublicFuction.getNCharInStr(a, 5, 5);
                b = PublicFuction.getNCharInStr(b, 5, 5);
                c = PublicFuction.getNCharInStr(c, 5, 5);
                kq.append("\n\tAmount: ").append(PublicFuction.getValueOrDefault(i.getAmount(), 0))
                        .append(" | FromAccount: ").append(a)
                        .append(" | ToAccount: ").append(b)
                        .append(" | Signature: ").append(c);
            }
        }
        return kq.toString();
    }
}
