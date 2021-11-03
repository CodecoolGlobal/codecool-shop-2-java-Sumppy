package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier empire = new Supplier("Empire", "Safe, reliable, fair empire");
        supplierDataStore.add(empire);
        Supplier ryloth = new Supplier("Ryloth", "Recourse wise a rich planet");
        supplierDataStore.add(ryloth);

        //setting up a new product category
        ProductCategory vehicle = new ProductCategory("Vehicle", "Transport", "A unique way to move around in the galaxy.");
        productCategoryDataStore.add(vehicle);
        ProductCategory weapon = new ProductCategory("Weapon", "War", "Used to kill enemy.");
        productCategoryDataStore.add(weapon);
        ProductCategory humanoid = new ProductCategory("Human", "Slave", "A person, who serves all your needs");
        productCategoryDataStore.add(humanoid);
        ProductCategory everyday = new ProductCategory("Everyday", "Everyday", "Objects for everyday use");
        productCategoryDataStore.add(everyday);

        //setting up products and printing it
        productDataStore.add(new Product("Tie-fighter", new BigDecimal("599"), "Cr", "Fantastic price. Barely used. Great for transportation. It is unarmed by the technical engineers of the Empire", vehicle, empire));
        productDataStore.add(new Product("Slave from Tatooine", new BigDecimal("99"), "Cr", "Loyal. Great way to cut down your grass. Cheap price for cheap work.                                   ", humanoid, ryloth));
        productDataStore.add(new Product("Battle Droid", new BigDecimal("30"), "Cr", "NOT WORKING. Comes without a weapon, great for melting down and forging something out of it.                  ", humanoid, empire));
        productDataStore.add(new Product("Vader school kit", new BigDecimal("15"), "Cr", "Best merch in the Galaxy! Show Lord Vader some support by buying this kit!!                               ", everyday, empire));
        productDataStore.add(new Product("Clone trooper blaster", new BigDecimal("49"), "Cr", "Old but gold. It is not working but a great way to remember what killed the Jedi ;)                  ", weapon, empire));
        productDataStore.add(new Product("Mercedes SW edition", new BigDecimal("499"), "Cr", "The best gag that the writer could find at 18:50 yesterday so don't even ask what the hek is this...", vehicle, empire));
    }
}
