import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<Block> blockList = PublicFuction.AddNewBlock(null);
        Block currentBlock = PublicFuction.getCurrentBlock(blockList);

        BlockDetail blockDetail = new BlockDetail(1L, "abc", "ncb");
        blockDetail.createSignature(currentBlock.getBlockId());
        currentBlock.AddBlockDetail(blockDetail);

        blockDetail = new BlockDetail(3L, "abc", "xyz");
        blockDetail.createSignature(currentBlock.getBlockId());
        currentBlock.AddBlockDetail(blockDetail);

        blockList = PublicFuction.AddNewBlock(blockList);
        currentBlock = PublicFuction.getCurrentBlock(blockList);

        blockDetail = new BlockDetail(2L, "abc", "xyz");
        blockDetail.createSignature(currentBlock.getBlockId());
        currentBlock.AddBlockDetail(blockDetail);

        blockDetail = new BlockDetail(1L, "abc", "xyz");
        blockDetail.createSignature(currentBlock.getBlockId());
        currentBlock.AddBlockDetail(blockDetail);

        blockDetail = new BlockDetail(7L, "abc", "xyz");
        blockDetail.createSignature(currentBlock.getBlockId());
        currentBlock.AddBlockDetail(blockDetail);

        List<Block> blockList1 = PublicFuction.CloneBlockList(blockList);
        List<Block> blockList2 = PublicFuction.CloneBlockList(blockList);

        List<List<Block>> allBlockList = new ArrayList<>();
        allBlockList.add(blockList);
        allBlockList.add(blockList1);
        allBlockList.add(blockList2);

        currentBlock = PublicFuction.getCurrentBlock(blockList1);
        currentBlock.setBlockId("2");
        System.out.println(blockList.get(1).getBlockId()+"-"+blockList1.get(1).getBlockId());

        List<Block> TrueBlock = PublicFuction.TrueBlockList(allBlockList);

        PublicFuction.ShowBlockList(TrueBlock);
    }
}