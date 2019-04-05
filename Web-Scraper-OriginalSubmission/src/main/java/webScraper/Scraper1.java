package webScraper;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * An example class that could scrape data from a single website.
 */
public class Scraper1 extends Thread {
    //Specifies the interval between HTTP requests to the server in seconds.
    private int crawlDelay = 1;
    
    //Allows us to shut down our application cleanly
    volatile private boolean runThread = false;
    
    @Override
    public void run(){
        runThread = true;
        
        //While loop will keep running until runThread is set to false;
        while(runThread){
            System.out.println("Scraper 1 thread is scraping data.");
            
          Document document1 = null;
            try {
                document1 = Jsoup.connect("https://www.chronext.co.uk/buy?q=hublot").get();

            } catch (IOException ex) {
                Logger.getLogger(Scraper1.class.getName()).log(Level.SEVERE, null, ex);
            }
        
      
        for (Element row : document1.select("div.products-list-watches.product-list-thumb a")) {

            //final String image1 = row.select(".products-list-watches.product-list-thumb img").attr("href");
            final String brand_1 = row.select(".product__brand").text();
            final String model_1 = row.select(".product__model").text();
            final String product_ref_1 = row.select(".product__ref").text();
            final String original_price_1 = row.select(".product__price del").text();
            final String sale_price_1 = row.select(".product__price-discount").text();
            
            System.out.println(
                    //"Img Url" + " : " + image1 + "\n" +
                    "Brand" + " : " + brand_1 + "\n"  +
                    "Model" + " : " + model_1 + "\n"  +
                    "Product Ref" + " : "  + product_ref_1 + "\n"  +
                    "Original Price" + " : " + original_price_1 + "\n"  +
                    "Sale Price" + " : " + sale_price_1 + "\n"  
            );
            
        }
            
            //Sleep for the crawl delay, which is in seconds
            try{
                sleep(1000 * crawlDelay);//Sleep is in milliseconds, so we need to multiply the crawl delay by 1000
            }
            catch(InterruptedException ex){
                System.err.println(ex.getMessage());
            }
        }                   
    }
        
    //Other classes can use this method to terminate the thread.
    public void stopThread(){
        runThread = false;
    }
}
