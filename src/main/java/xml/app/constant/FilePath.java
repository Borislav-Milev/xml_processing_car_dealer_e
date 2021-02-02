package xml.app.constant;

public class FilePath {

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\EX2\\";
    private static final String VIEWS_FILE_PATH = FILE_PATH + "views\\";

    //Seed file paths
    public static final String CARS_PATH = FILE_PATH + "cars.xml";
    public static final String CUSTOMERS_PATH = FILE_PATH + "customers.xml";
    public static final String PARTS_PATH = FILE_PATH + "parts.xml";
    public static final String SUPPLIERS_PATH = FILE_PATH + "suppliers.xml";

    //Views
    public static final String ORDERED_CUSTOMERS_VIEW = VIEWS_FILE_PATH + "query1.xml";
    public static final String CARS_FROM_MAKE_TOYOTA_VIEW  = VIEWS_FILE_PATH + "query2.xml";
    public static final String LOCAL_SUPPLIERS_VIEW  = VIEWS_FILE_PATH + "query3.xml";
    public static final String CARS_WITH_PARTS_VIEW  = VIEWS_FILE_PATH + "query4.xml";
    public static final String CUSTOMERS_TOTAL_SALES_VIEW  = VIEWS_FILE_PATH + "query5.xml";
    public static final String SALES_DISCOUNTS_VIEW  = VIEWS_FILE_PATH + "query6.xml";
}