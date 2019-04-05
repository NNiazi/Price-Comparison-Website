package webScraper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
     
       //runApplicationsXMLConfig();
       runApplicationsXMLConfig();
    }
    
    /** Uses Spring XML configuration to set up and run application */
    static void runApplicationsXMLConfig(){
        
        //Instruct Spring to create and wire beans using XML file
        ApplicationContext context = new ClassPathXmlApplicationContext("/src/main/webScraper/beans.xml");
        
        //Get scraper bean
        ScraperManager scraperManager = (ScraperManager) context.getBean("/scraperManager");
        
        //Call methods on scraper bean
        scraperManager.startScraping();
   }
}

