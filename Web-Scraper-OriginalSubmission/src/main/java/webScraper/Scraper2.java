package webScraper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Scraper2 extends Thread {
    //Specifies the interval between HTTP requests to the server in seconds.
    private int crawlDelay = 2;
    
    //Allows us to shut down our application cleanly
    volatile private boolean runThread = false;
    
    @Override
    public void run(){
        runThread = true;
        
        //While loop will keep running until runThread is set to false;
        while(runThread){
            System.out.println("Scraper 4 thread is scraping data.");
            
            Document document2 = null;
              try {
                   document2 = Jsoup.connect("https://www.chrono24.com/search/index.htm?dosearch=true&manufacturerIds=18&maxAgeInDays=0&models=116&pageSize=60&priceFrom=0&priceTo=-1&redirectToSearchIndex=true&sortorder=0").timeout(6000).get();

              } catch (IOException ex) {
                  Logger.getLogger(Scraper4.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            /*Elements images = document1.getElementsByTag("img");
            
            for(Element src : images){
                System.out.println("src : " + src.attr("abs:src"));
            }*/
            
            //Elements which are being scraped from the search results
            Elements ele = document2.select("div.full-xs");
            for (Element row : ele.select(".article-item-container")) {
                
                String brandAndModel = row.select(".article-title").text();
                String seller = row.select(".article-seller-name.text-ellipsis").text();
                String price = row.select("strong").text();
                
                System.out.println(
                        "Brand : " + brandAndModel + "\n" +
                        "Ref : " + seller + "\n" +
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