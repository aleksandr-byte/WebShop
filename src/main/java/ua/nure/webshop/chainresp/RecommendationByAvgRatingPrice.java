package ua.nure.webshop.chainresp;

import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.utilityfunction.UtilityFunction;
import ua.nure.webshop.utilityfunction.model.FunctionUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class RecommendationByAvgRatingPrice extends Recommendation {

    public RecommendationByAvgRatingPrice(Recommendation next) {
        super(next);
    }

    public RecommendationByAvgRatingPrice() {
    }

    @Override
    protected List<Products> recommendation(List<Products> inputProducts, User user, List<Products> productsToCompare) {

        List<FunctionUtility> functionUtilities = UtilityFunction.getLocalUtility(inputProducts);

        functionUtilities.forEach(functionUtility -> System.out.println(functionUtility.getProduct().getName() + " |K=" + functionUtility.getK() + "| P1:" + functionUtility.getP1() + ", P2:" + functionUtility.getP2()));

        Comparator<FunctionUtility> comparator = Comparator
                .comparing(FunctionUtility::getK)
                .reversed();

        List<FunctionUtility> sortedFunctionUtilities = functionUtilities.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        List<Products> recItem = new ArrayList();
        sortedFunctionUtilities.forEach(functionUtility -> recItem.add(functionUtility.getProduct()));
        printResult(recItem);
        return setRecommendation(recItem, user, productsToCompare);
    }

    private void printResult(List<Products> recItem) {
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("Products rec. by def(AVG rating and min price)");
        System.out.println("----------------------------------------------------");
        recItem.forEach(product -> System.out.println(product.getName()));
        System.out.println("----------------------------------------------------");
    }
}
