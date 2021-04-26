package ua.nure.webshop.utilityfunction;

import ua.nure.webshop.domain.Products;
import ua.nure.webshop.utilityfunction.model.FunctionUtility;

import java.util.ArrayList;
import java.util.List;

public class UtilityFunction {

    private static double getMinRating(List<Products> products) {
        return products.stream().mapToInt(product -> product.getRating()).min().getAsInt();
    }

    private static double getMaxRating(List<Products> products) {
        return products.stream().mapToInt(product -> product.getRating()).max().getAsInt();
    }

    private static double getMinPrice(List<Products> products) {
        return products.stream().mapToDouble(product -> product.getPrice().intValue()).min().getAsDouble();
    }

    private static double getMaxPrice(List<Products> products) {
        return products.stream().mapToDouble(product -> product.getPrice().intValue()).max().getAsDouble();
    }

    public static List<FunctionUtility> getLocalUtility(List<Products> products) {
        List<FunctionUtility> utilities = new ArrayList();
        double minK1 = getMinRating(products);
        double maxK1 = getMaxRating(products);
        double minK2 = getMinPrice(products);
        double maxK2 = getMaxPrice(products);

        products.forEach(product -> {
            FunctionUtility functionUtility = new FunctionUtility();
            functionUtility.setProduct(product);
            if (product.getRating() == 0) {
                functionUtility.setP1(0.0);
            }
            else {
                functionUtility.setP1((product.getRating() - minK1) / (maxK1 - minK1));
            }
            functionUtility.setP2((product.getPrice().doubleValue() - maxK2) / (minK2 - maxK2));
            functionUtility.setK(functionUtility.getP1() * 0.6 + functionUtility.getP2() * 0.4);
            utilities.add(functionUtility);
        });
        return utilities;
    }

}
