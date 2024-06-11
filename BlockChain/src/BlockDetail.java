public class BlockDetail {
    private Long amount;
    private String fromAccount;
    private String toAccount;
    private String signature;
    public BlockDetail(Long amount, String fromAccount, String toAccount){
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }
    public void createSignature(String privateKey){
        if(amount == null || fromAccount == null || toAccount == null){
            return;
        }
        signature = HashFunction.SHA_512(amount+"-"+fromAccount+"-"+toAccount, HashFunction.SHA_256(privateKey, privateKey));
    }

    public Long getAmount() {
        return amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getSignature() {
        return signature;
    }
}
