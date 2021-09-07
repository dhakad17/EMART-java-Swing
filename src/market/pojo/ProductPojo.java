package market.pojo;

public class ProductPojo {

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public ProductPojo()
    {
        
    }

    public ProductPojo(String productId, String productName, String productComapny, double productPrice, double ourPrice, int Tax, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productComapny = productComapny;
        this.productPrice = productPrice;
        this.ourPrice = ourPrice;
        this.Tax = Tax;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductComapny() {
        return productComapny;
    }

    public void setProductComapny(String productComapny) {
        this.productComapny = productComapny;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public int getTax() {
        return Tax;
    }

    public void setTax(int Tax) {
        this.Tax = Tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    private String productId;
    private String productName;
    private String productComapny;
    private double productPrice;
    private double ourPrice;
    private int Tax;
    private int quantity;
    private double total;
}
