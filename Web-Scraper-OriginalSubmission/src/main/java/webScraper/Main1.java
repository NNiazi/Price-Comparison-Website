package webScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.util.Scanner;

/**
 * Main class for example threaded web scraper
 */
public class Main1 {
    public static void main(String[] args){
        //Create the scraper classes
        Scraper1 scraper1 = new Scraper1();
        Scraper2 scraper2 = new Scraper2();
        Scraper3 scraper3 = new Scraper3();
        Scraper4 scraper4 = new Scraper4();
        Scraper5 scraper5 = new Scraper5();
        Scraper6 scraper6 = new Scraper6();
        Scraper7 scraper7 = new Scraper7();

        //Start the threads running.
        scraper1.start();
        scraper2.start();
        scraper3.start();
        scraper4.start();
        scraper5.start();
        scraper6.start();
        scraper7.start();
        
        
        //Read input from user until they type 'stop'
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while(!userInput.equals("stop")){
            userInput = scanner.nextLine();
        }
        
        //Stop threads
        scraper1.stopThread();
        scraper2.stopThread();
        scraper3.stopThread();
        scraper4.stopThread();
        scraper5.stopThread();
        scraper6.stopThread();
        scraper7.stopThread();
        
        
        //Wait for threads to finish - join can throw an InterruptedException
        try{
            scraper1.join();
            scraper2.join();
            scraper3.join();
            scraper4.join();
            scraper5.join();
            scraper6.join();
            scraper7.join();
        }
        catch(InterruptedException ex){
            System.out.println("Interrupted exception thrown: " + ex.getMessage());
        }
        
        System.out.println("Web scraping complete");
    }
}

