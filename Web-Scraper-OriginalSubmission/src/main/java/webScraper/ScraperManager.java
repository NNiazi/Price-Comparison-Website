package webScraper;

public class ScraperManager {
    
    Scraper1 scraper1;
    Scraper2 scraper2;
    Scraper3 scraper3;
    Scraper4 scraper4;
    Scraper5 scraper5;
    Scraper6 scraper6;
    Scraper7 scraper7;
   
    public ScraperManager(){
        
    }
    
    void startScraping(){
        scraper1.start();
        scraper2.start();
        scraper3.start();
        scraper4.start();
        scraper5.start();
        scraper6.start();
        scraper7.start();
        
    }
    
  

    public Scraper1 getScraper1() {
        return scraper1;
    }

    public void setScraper1(Scraper1 scraper1) {
        this.scraper1 = scraper1;
    }

     public Scraper2 getScraper2() {
        return scraper2;
    }

    public void setScraper2(Scraper2 scraper2) {
        this.scraper2 = scraper2;
    }

    public Scraper3 getScraper3() {
        return scraper3;
    }

    public void setScraper3(Scraper3 scraper3) {
        this.scraper3 = scraper3;
    }
    
     public Scraper4 getScraper4() {
        return scraper4;
    }

    public void setScraper4(Scraper4 scraper4) {
        this.scraper4 = scraper4;
    }
    
     public Scraper5 getScraper5() {
        return scraper5;
    }

    public void setScraper5(Scraper5 scraper5) {
        this.scraper5 = scraper5;
    }
    
     public Scraper6 getScraper6() {
        return scraper6;
    }

    public void setScraper6(Scraper6 scraper6) {
        this.scraper6 = scraper6;
    }
    
     public Scraper7 getScraper7() {
        return scraper7;
    }

    public void setScraper7(Scraper7 scraper7) {
        this.scraper7 = scraper7;
    }
    
}
