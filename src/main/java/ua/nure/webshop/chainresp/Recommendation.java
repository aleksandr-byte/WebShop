package ua.nure.webshop.chainresp;

import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.repos.OrderRepository;

import java.util.List;

public abstract class Recommendation {

    private Recommendation next;
    private List<Products> products;

    public Recommendation(Recommendation next) {
        this.next = next;
    }

    public Recommendation() {
    }

    protected abstract List<Products> recommendation(List<Products> products, User user, OrderRepository orderRepository);

    public List<Products> setRecommendation(List<Products> products, User user, OrderRepository orderRepository) {
        List<Products> recItem = this.recommendation(products, user, orderRepository);
        if (next != null) {
            next.setRecommendation(products, user, orderRepository);
        }
        return recItem;
    }

}
