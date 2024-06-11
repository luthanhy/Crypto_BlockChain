// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Block block1 = new Block();
        Block block2 = new Block();
        block1.setBlockId(HashFunction.SHA_512("abc", ""));

        Long amount = 2L;
        String fromAccount = HashFunction.SHA_512("abc", "");
        String toAccount = HashFunction.SHA_512("bac", "");
        String signature = HashFunction.SHA_512("2-abc-bac", "");
        BlockDetail bd = new BlockDetail(amount, fromAccount, toAccount, signature);
        block1.AddBlockDetail(bd);
        bd = new BlockDetail(3L, "abc", "nca", HashFunction.SHA_512("2-abc-nca", ""));
        block1.AddBlockDetail(bd);
        System.out.println(block1);
        block2.setPrevHash(block1.getHashBlock());
        System.out.println(block2);
    }
}