package ua.nure.webshop.chainresp;

import org.apache.commons.text.similarity.CosineSimilarity;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.User;

import java.util.*;

public class RecommendationByUserOrders extends Recommendation{

    @Override
    protected List<Products> recommendation(List<Products> inputProducts, User user, List<Products> productsToCompare) {
            // If client make more than 3 purchases, system will give him recommendations by his purchases
            System.out.println("Products size: " + productsToCompare.size());
            if (productsToCompare.size() > 0) {
                StringBuilder query = new StringBuilder();
                for (Products products: productsToCompare) {
                    query.append(products.getName()).append(" ");
                }

                List<Products> recProducts = getRecommendation(query.toString(), inputProducts);
                printResult(recProducts);
                return recProducts;
            }
            else {
                return inputProducts;
            }
    }

    private List<Products> getRecommendation(String query, List<Products> products) {
        Map<CharSequence, Integer> queryVector = getVector(query);

        products.stream().forEach(product ->
                product.setCosineSimilarity(compareVectors(queryVector, getVector(product.getName()))));

        List<Products> modifiableProductsList = new ArrayList(products);

        Collections.sort(modifiableProductsList, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return o1.getCosineSimilarity() <  o2.getCosineSimilarity() ? 1:-1;
            }
        });
        return modifiableProductsList;
    }

    private Map<CharSequence, Integer> getVector(String str) {
        Map<CharSequence, Integer> vector = new HashMap<CharSequence, Integer>();
        for (String token : str.split(" ")) {
            vector.put(token, vector.getOrDefault(token, 0) + 1);
        }
        return vector;
    }

    private Double compareVectors(Map<CharSequence, Integer> queryVector, Map<CharSequence, Integer> vector) {
        CosineSimilarity cosine = new CosineSimilarity();
        return cosine.cosineSimilarity(queryVector, vector);
    }

    private void printResult(List<Products> recProducts){
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("Products rec. by client preferences");
        System.out.println("----------------------------------------------------");
        recProducts.forEach(product -> System.out.println(product.getName() + " = " + product.getCosineSimilarity()));
        System.out.println("----------------------------------------------------");
    }
}
