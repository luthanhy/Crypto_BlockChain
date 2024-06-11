public class BlockDetail {
    private Long amount;
    private String fromAccount;
    private String toAccount;
    private String signature;
    public BlockDetail(Long amount, String fromAccount, String toAccount, String signature){
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.signature = signature;
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
