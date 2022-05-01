package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    ProductsRepository productsRepository;
    EmbeddedDatabase dataSource;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "iPhone", 75000),
            new Product(2L, "iPad", 60000),
            new Product(3L, "iMac", 135000),
            new Product(4L, "iPod", 27000),
            new Product(5L, "iWatch", 45000)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "iPhone", 75000);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "iMac", 135000);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "AirPods", 23000);

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testFindAll() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void testFindById() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(1L).get());
    }

    @Test
    void testUpdate() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(3L).get());
    }

    @Test
    void testSave() throws SQLException {
        productsRepository.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, productsRepository.findById(6L).get());
    }

    @Test
    void testDelete() throws SQLException {
        productsRepository.delete(1L);
        Assertions.assertThrows(RuntimeException.class, () -> productsRepository.findById(1L));
    }

    @AfterEach
    void close() {
        dataSource.shutdown();
    }

}
