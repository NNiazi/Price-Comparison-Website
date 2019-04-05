package webScraper;

/** Represents a Cereal */
public class WatchShopXML {
    private int id;
    private int modelId;
    private int brandId;
    private int urlId;
    private float price;
    private String description;
    
    
    /** Empty constructor */
    public WatchShopXML(){
    }

    
    //Getters and setters
    public int getId() {
        return id;
    }
    public int getModelId() {
        return modelId;
    }
    public int getBrandId() {
        return brandId;
    }
    public int getUrlID() {
        return urlId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setModelId(int productTypeId) {
        this.modelId = modelId;
    }
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    /** Returns a String description of the class */
    public String toString(){
        String str = "WatchShop. id: " + id + "; modelId: " + modelId + "; brandId: " +
                 brandId + "; urlId: " + urlId + "; price: " + price + "; description: " + description;
        return str;
    } 
}
