package webScraper;

//Hibernate imports
import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;


/** Simple Hibernate example that uses XML files to specify the mapping between 
 *  a Watch object and the Watches table in the price_comparison database. */
public class HibernateXML {
    //Use this class to create new Sessions to interact with the database 
    private SessionFactory sessionFactory;
    
    
    /** Empty constructor */
    HibernateXML() {
    }

    
    /** Sets up the session factory.
     *  Call this method first.
     */
    public void init(){
        try {
            //Create a builder for the standard service registry
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            //Load configuration from hibernate configuration file
            standardServiceRegistryBuilder.configure("resources/hibernate.cfg.xml"); 

            //Create the registry that will be used to build the session factory
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
            
            try {
                //Create the session factory - this is the goal of the init method.
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                    /* The registry would be destroyed by the SessionFactory, 
                        but we had trouble building the SessionFactory, so destroy it manually */
                    System.err.println("Session Factory build failed.");
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy( registry );
            }

            //Ouput result
            System.out.println("Session factory built.");

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
        }
    }
    
    
    /** Closes Hibernate down and stops its threads from running*/
    public void shutDown(){
        sessionFactory.close();
    }

    
    /** Adds a new watch to the database */
    public void addWatchShop(){
        //Get a new Session instance from the SessionFactory
        Session session = sessionFactory.getCurrentSession();

        //Create an instance of a Watch class 
        WatchShopXML watchShop = new WatchShopXML();

        //Set values of Watch class that we want to add
        watchShop.setBrandId(1);
        watchShop.setModelId(1);
        watchShop.setUrlId(1);
        watchShop.setPrice(30.5f);
        watchShop.setDescription("Cartier");

        //Start transaction
        session.beginTransaction();

        //Add Watch to database - will not be stored until we commit the transaction
        session.save(watchShop);

        //Commit transaction to save it to database
        session.getTransaction().commit();
        
        //Close the session and release database connection
        session.close();
        System.out.println("Watch added to database with ID: " + watchShop.getId());
    }
    
    
    /** Updates the values of an existing watch in the database */
    public void updateWatchShop(){
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();

        //Create an instance of a Watch class 
        WatchShopXML watchShop = new WatchShopXML();
        
        //Set ID of watch class - this controls the row in the table that we want to update
        watchShop.setId(16);

        //Set new values of Watch class that we want to add
        watchShop.setBrandId(1);
        watchShop.setModelId(1);
        watchShop.setUrlId(1);
        watchShop.setPrice(40.5f);
        watchShop.setDescription("hublot");

        //Start transaction
        session.beginTransaction();

        //Update database to match class 
        session.update(watchShop);
        session.getTransaction().commit();
        
        //Close the session and release database connection
        session.close();
        System.out.println("Watch updated in database. ID: " + watchShop.getId());
    }
    
    
    /** Searches for Watch whose price is 5.5 */
    public void searchWatches(){
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        List<WatchShopXML> watchList = session.createQuery("from WatchShopXml where price=5.5").getResultList();
        for(WatchShopXML watch : watchList){
            System.out.println(watch.toString());
        }
        
        //Close the session and release database connection
        session.close();
    }

    
    /** Deletes an object without a foreign key from the database 
        This will generate an error if there is a foreign key. */
    public void deleteWatchShopSimple(){
        //Create Watch class with the ID that we want to delete
        WatchShopXML watchShop = new WatchShopXML();
        watchShop.setId(5);
        
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();
        
        //Start transaction
        session.beginTransaction();

        //Update database to match class 
        session.delete(watchShop);
        session.getTransaction().commit();
        
        //Close the session and release database connection
        session.close();
        System.out.println("Watch updated in database. ID: " + watchShop.getId());
    }
    
    
    /** Deletes a Watch in a way that will work with tables with foreign keys */
    public void deleteWatchShopSafe(){
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();
        
        //Start transaction
        session.beginTransaction();
        
        //Search for watch in database that has ID of 6
        Object persistentInstance = session.load(WatchShopXML.class, 16);
        
        //Delete object (and corresponding data) if we have found a match.
        if (persistentInstance != null) 
            session.delete(persistentInstance);

        //Update database
        session.getTransaction().commit();
        
        //Close the session and release database connection
        session.close();
        System.out.println("Watch updated in database. ID: 6");
    }

}