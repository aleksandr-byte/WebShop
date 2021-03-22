package ua.nure.webshop.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.service.RecommendationService;
import ua.nure.webshop.utilityfunction.UtilityFunction;
import ua.nure.webshop.utilityfunction.model.FunctionUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultRecommendationServiceImpl implements RecommendationService {

    @Override
    public List<Products> getItems(List<Products> products) {
        List<FunctionUtility> functionUtilities = UtilityFunction.getLocalUtility(products);

        Comparator<FunctionUtility> comparator = Comparator
                .comparing(FunctionUtility::getP1)
                .thenComparing(FunctionUtility::getP2)
                .reversed();

        List<FunctionUtility> sortedFunctionUtilities = functionUtilities.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        List<Products> recItem = new ArrayList();
        sortedFunctionUtilities.forEach(functionUtility -> recItem.add(functionUtility.getProduct()));
        return recItem;
    }
}
