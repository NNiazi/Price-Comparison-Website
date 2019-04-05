package webScraper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;    
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Scraper5 extends Thread {
    //Specifies the interval between HTTP requests to the server in seconds.
    private int crawlDelay = 5;
    
    //Allows us to shut down our application cleanly
    volatile private boolean runThread = false;
    
    @Override
    public void run(){
        runThread = true;
        
        //While loop will keep running until runThread is set to false;
        while(runThread){
            System.out.println("Scraper 5 thread is scraping data.");
            
            Document document5 = null;
              try {
                   document5 = Jsoup.connect("https://www.berrysjewellers.co.uk/watches-c10/patek-philippe-m44").timeout(6000).get();

              } catch (IOException ex) {
                  Logger.getLogger(Scraper5.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            /*Elements images = document1.getElementsByTag("img");
            
            for(Element src : images){
                System.out.println("src : " + src.attr("abs:src"));
            }*/
            
            //Elements which are being scraped from the search results
            Elements ele = document5.select("div.search-results-products.search-results-grid");
            for (Element row : ele.select(".col.l-col-8.m-col-third.s-col-16")) {
                
                String brand = row.select("a.infclick").text();
                String price = row.select(".GBP").text();
                
                System.out.println(
                        "Brand : " + brand + "\n" +
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