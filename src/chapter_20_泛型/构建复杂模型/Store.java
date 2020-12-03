package chapter_20_泛型.构建复杂模型;

import chapter_20_泛型.Suppliers;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

/**
 * 该模型是一个具有过道，货架和产品的零售商店
 */

// 商品（商品）
class Product {
    private final int id;
    private String description;
    private double price;

    Product(int idNumber, String descr, double price) {
        id = idNumber;
        description = descr;
        this.price = price;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Product{ " + id + ": " + description + ", price: $" + price + " }";
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Supplier<Product> generator = new Supplier<Product>() {
        private Random rand = new Random(47);
        @Override
        public Product get() {
            return new Product(rand.nextInt(1000), "Test",
                    Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };
}

// 货架
class Shelf extends ArrayList<Product> {
    Shelf(int nProduct) {
        Suppliers.fill(this, Product.generator, nProduct);
    }
}

// 过道
class Aisle extends ArrayList<Shelf> {
    Aisle(int nShelves, int nProducts) {
        for (int i = 0; i < nShelves; i++) {
            add(new Shelf(nProducts));
        }
    }
}

// 检验台
class CheckoutStand {}
// 办公室
class Office {}

// 商店
public class Store extends ArrayList<Aisle> {
    private ArrayList<CheckoutStand> checkouts = new ArrayList<>();
    private Office office = new Office();

    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves, nProducts));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Aisle a : this) {
            for (Shelf s : a) {
                for (Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(5, 4, 3));
    }
}
