package ua.nure.webshop.chainresp;

import org.apache.commons.text.similarity.CosineSimilarity;
import ua.nure.webshop.domain.Products;
import ua.nure.webshop.domain.Role;
import ua.nure.webshop.domain.User;
import ua.nure.webshop.repos.OrderRepository;

import java.util.*;

public class RecommendationByUserOrders extends Recommendation{

    @Override
    protected List<Products> recommendation(List<Products> products, User user, OrderRepository orderRepository) {
        if(user != null && user.getRoles().contains(Role.USER)){
            List<String> productsName = new ArrayList();
            List<String> ordersID = orderRepository.findOrdersByUserID(user.getId());
            for(String orderID: ordersID){
                orderRepository.findOrderProductsByOrderID(orderID).forEach(productName -> productsName.add(productName));
            }

            // If client make more than 3 purchases, system will give him recommendations by his purchases
            System.out.println("Products name size: " + productsName.size());
            if (productsName.size() > 3) {
                StringBuilder query = new StringBuilder();
                for (String name: productsName) {
                    query.append(name).append(" ");
                }

                products = getRecommendation(query.toString(), products);
                System.out.println("///////////////////////////////////////////////////");
                System.out.println("Products rec. by client preferences");
                System.out.println("----------------------------------------------------");
                products.forEach(product -> System.out.println(product.getName() + " = " + product.getCosineSimilarity()));
                System.out.println("----------------------------------------------------");
                return products;
            }
        }
        return products;
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
}
