package ua.nure.webshop.chainresp;

import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;

import java.util.List;

public class StartChain extends Recommendation{

    public StartChain(Recommendation next) {
        super(next);
    }

    @Override
    protected List<Products> recommendation(List<Products> inputProducts, User user, List<Products> productsToCompare) {
        return setRecommendation(inputProducts, user, productsToCompare);
    }


}
