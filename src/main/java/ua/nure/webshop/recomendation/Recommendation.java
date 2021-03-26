package ua.nure.webshop.recomendation;

import org.apache.commons.text.similarity.CosineSimilarity;
import ua.nure.webshop.domain.Products;

import java.util.*;

public class Recommendation {

    public List<Products> getRecommendation(String query, List<Products> products) {
        Map<CharSequence, Integer> queryVector = getVector(query);

        products.stream().forEach(product ->
                product.setCosineSimilarity(compareVectors(queryVector, getVector(product.getName()))));

        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return o1.getCosineSimilarity() <  o2.getCosineSimilarity() ? 1:-1;
            }
        });
        return products;
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

}
