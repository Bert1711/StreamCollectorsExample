package com.zaroyan;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zaroyan
 */
class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        // Группируем, суммируем, сортируем и выбираем топ-3 продукта в одном потоке
        List<Order> topThreeProducts = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,
                        Collectors.summingDouble(Order::getCost)))
                .entrySet().stream()
                .map(entry -> new Order(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingDouble(Order::getCost).reversed())
                .limit(3)
                .collect(Collectors.toList());

        // Выводим результат
        System.out.println("Топ 3 самых дорогих продуктов и их общая стоимость:");
        topThreeProducts.forEach(order ->
                System.out.println("Продукт: " + order.getProduct() + ", Общая стоимость: " + order.getCost()));
    }
}