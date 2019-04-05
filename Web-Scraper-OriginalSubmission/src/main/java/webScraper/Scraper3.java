package webScraper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Scraper3 extends Thread {
    //Specifies the interval between HTTP requests to the server in seconds.
    private int crawlDelay = 3;
    
    //Allows us to shut down our application cleanly
    volatile private boolean runThread = false;
    
    @Override
    public void run(){
        runThread = true;
        
        //While loop will keep running until runThread is set to false;
        while(runThread){
            System.out.println("Scraper 3 thread is scraping data.");
            
            Document document3 = null;
              try {
                   document3 = Jsoup.connect("https://www.beaverbrooks.co.uk/watches/pre-owned-watches/rolex-watches?q=:favourites122:gender:MALE").timeout(6000).get();

              } catch (IOException ex) {
                  Logger.getLogger(Scraper3.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            /*Elements images = document1.getElementsByTag("img");
            
            for(Element src : images){
                System.out.println("src : " + src.attr("abs:src"));
            }*/
            
            //Elements which are being scraped from the search results
            Elements ele = document3.select("div.product-list__body.row.small-up-1.medium-up-2.large-up-3.xlarge-up-4");
            for (Element row : ele.select(".column.column-block")) {
                
                String brand = row.select(".product-list__item-brand").text();
                String model = row.select(".product-list__item-name").text();
                String price = row.select(".price.prod-price-offer.product-list__item-price ").text();
                
                System.out.println(
                        "Brand : " + brand + "\n" +
                        "Model : " + model + "\n" +
                        "Price : " + price + "\n" 
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