package ua.nure.webshop.chainresp;

import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;

import java.util.List;

public abstract class Recommendation {

    private Recommendation next;

    public Recommendation(Recommendation next) {
        this.next = next;
    }

    public Recommendation() {
    }

    protected abstract List<Products> recommendation(List<Products> inputProducts, User user, List<Products> productsToCompare);

    public List<Products> setRecommendation(List<Products> inputProducts, User user, List<Products> productsToCompare) {
        if (next == null) {
            return this.recommendation(inputProducts, user, productsToCompare);
        }
        return next.recommendation(inputProducts, user, productsToCompare);
    }

}
