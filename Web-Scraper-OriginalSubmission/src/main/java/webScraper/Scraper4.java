package webScraper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Scraper4 extends Thread {
    //Specifies the interval between HTTP requests to the server in seconds.
    private int crawlDelay = 4;
    
    //Allows us to shut down our application cleanly
    volatile private boolean runThread = false;
    
    @Override
    public void run(){
        runThread = true;
        
        //While loop will keep running until runThread is set to false;
        while(runThread){
            System.out.println("Scraper 4 thread is scraping data.");
            
            Document document4 = null;
              try {
                   document4 = Jsoup.connect("https://www.watchfinder.co.uk/search?q=Cartier&filterGender=Gents&filterSeries=Santos&orderby=BestMatch").timeout(6000).get();

              } catch (IOException ex) {
                  Logger.getLogger(Scraper4.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            /*Elements images = document1.getElementsByTag("img");
            
            for(Element src : images){
                System.out.println("src : " + src.attr("abs:src"));
            }*/
            
            //Elements which are being scraped from the search results
            Elements ele = document4.select("div.products_");
            for (Element row : ele.select(".col-prod.col-md-3")) {
                
                String brand = row.select(".prods_brand.ellipsis").text();
                String model = row.select(".prods_series.ellipsis").text();
                String ref = row.select(".prods_model.ellipsis").text();
                String price = row.select(".prods_price").text();
                
                System.out.println(
                        "Brand : " + brand + "\n" +
                        "Model : " + model + "\n" +
                        "Ref : " + ref + "\n" +
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