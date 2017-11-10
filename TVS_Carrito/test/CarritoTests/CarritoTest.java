/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarritoTests; 

import logica.FactoryCarrito;
import logica.ICarrito;
import logica.Producto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author ssainz
 */
public class CarritoTest {
    
    private ICarrito cart;
    private static Producto testProduct;
    
    private static final String TEST_PRODUCT_NAME = "default_product";
    private static final double TEST_PRODUCT_PRICE = 50;
    private static final int TEST_PRODUCT_QTY = 4;
    private static final double DELTA = 1e-10;
    
    @BeforeClass
    public static void beforeAllTests() {
        testProduct = new Producto(TEST_PRODUCT_PRICE, TEST_PRODUCT_NAME);
    }
    
    @AfterClass
    public static void afterAllTests() {
        testProduct = null;
    }
    
    @Before
    public void beforeEachTests() {
        cart = FactoryCarrito.getCarrito();
    }
    
    @After
    public void afterEachTest() {
        cart.vaciar();
    }
    
    /**
     * @testProduct: Un producto de test.
     * Se chequea que se agrega correctamente un producto que no existe en el carrito aún.
     */
    @Test
    public void testAddProductNotInCart(){
        
        // Action
        cart.agregarProducto(testProduct, TEST_PRODUCT_QTY);
        
        // Assertion
        Assert.assertEquals(TEST_PRODUCT_QTY, cart.obtenerCantidad(TEST_PRODUCT_NAME));
    }
    
    /**
     * Se chequea que si se invoca agregarProducto con cantidad 0 no se agregue el producto.
     * 
     * @testProduct: Un producto de test.
     */
    @Test
    public void testAddProductWithQuantityZero(){
        
        // Action
        cart.agregarProducto(testProduct, 0);
        
        // Assertion
        Assert.assertEquals(0, cart.obtenerCantidad(TEST_PRODUCT_NAME));
    }
    
    /**
     * Se chequea que al agregar una cantidad n a un producto ya existente, se modifique la cantidad. 
     * El resultado debería ser la cantidad actual + n.
     * 
     * @testProduct: Un producto de test.
     * @quantityToAdd: Cantidad a agregar del producto ya existente en el carrito.
     * 
     * NOTA: Este test falla con la implementación actual, lo cual es correcto.
     */
    @Ignore
    @Test
    public void testAddProductInCart(){
        
        // Setup
        cart.agregarProducto(testProduct, TEST_PRODUCT_QTY);
        int quantityToAdd = 3;
        
        // Action
        cart.agregarProducto(testProduct, quantityToAdd);
        
        // Assertion
        Assert.assertEquals(TEST_PRODUCT_QTY + quantityToAdd, cart.obtenerCantidad(TEST_PRODUCT_NAME));
    }
    
    /**
     * Se chequea que el precio total del carrito sea 0 cuando el carrito está vacío.
     * 
     * NOTA: Este test falla con la implementación actual, lo cual es correcto.
     */
    @Ignore
    @Test
    public void testObtenerprecioTotalWithEmptyCart() {
       
       // Action
       double totalPrice = cart.obtenerPrecioTotal();
       
       // Assertion
       Assert.assertEquals(0, totalPrice, DELTA);
    }
    
    
    /**
     * Se chequa que el precio total del carrito sea el valor del item por la cantidad del item.
     * Además, el subtotal del único item en el carrito debería ser igual al precio total.
     * 
     * @totalPrice: Precio total del carrito
     * @subtotal: Subtotal del carrito para el producto testProduct
     * 
     * NOTA: Este test falla con la implementación actual, lo cual es correcto.
     */
    @Ignore
    @Test
    public void testObtenerprecioTotalWithOneItem() {
        
       // Setup
       cart.agregarProducto(testProduct, TEST_PRODUCT_QTY);
       
       // Action
       double totalPrice = cart.obtenerPrecioTotal();
       
       // Assertion
       Assert.assertEquals(TEST_PRODUCT_PRICE * TEST_PRODUCT_QTY, totalPrice, DELTA);
       Assert.assertEquals(totalPrice, cart.obtenerSubtotal(TEST_PRODUCT_NAME), DELTA);
    }
    
    /**
     * Se chequa que el precio total del carrito sea el valor de los items por la cantidad de cada uno.
     * 
     * @expectedTotal: El valor esperado como total del carrito
     * 
     * NOTA: Este test falla con la implementación actual, lo cual es correcto.
     */
    @Ignore
    @Test
    public void testObtenerProductoTotalWithMultipleItems() {
        
        Producto shampooTest = new Producto(10, "Shampoo");
        Producto soapTest = new Producto(20, "Soap");
        Producto toothpasteTest = new Producto(30, "Toothpaste");
        
        cart.agregarProducto(shampooTest, 2);
        cart.agregarProducto(soapTest, 2);
        cart.agregarProducto(toothpasteTest, 2);
        
        double expectedTotal = 2*10 + 2*20 + 2*30;
        
        Assert.assertEquals(expectedTotal, cart.obtenerPrecioTotal(), DELTA);
    }
}
