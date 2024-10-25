package kr.or.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PeteCakesTest {

    /**
     * Pete likes to bake some cakes. He has some recipes and ingredients. Unfortunately he is not good in maths. Can you help him to find out, how many cakes he could bake considering his recipes?
     *
     * Write a function cakes(), which takes the recipe (object) and the available ingredients (also an object) and returns the maximum number of cakes Pete can bake (integer). For simplicity there are no units for the amounts (e.g. 1 lb of flour or 200 g of sugar are simply 1 or 200). Ingredients that are not present in the objects, can be considered as 0.
     * */

    private int cakes(Map<String, Integer> recipe, Map<String, Integer> available) {

        int cakeCnt = 0;
        int intBucket = 0;
        int minimumCnt = 0;

        // 사용 가능한 재료에 레시피가 모두 포함되어있는지 확인되어야함.
        // 재료가 있다면 최소배수가 케이크를 만들 수 있는 개수임.
        for(Map.Entry<String, Integer> entry : recipe.entrySet()) {

            intBucket = available.getOrDefault(entry.getKey(), 0);

            if(intBucket == 0) {
                minimumCnt = intBucket;
                break;
            } else {
                cakeCnt = intBucket / entry.getValue();
                if(cakeCnt == 0) {
                    minimumCnt = cakeCnt;
                    break;
                } else {
                    if(minimumCnt == 0 || minimumCnt > cakeCnt) minimumCnt = cakeCnt;
                }
            }
        }

        return minimumCnt;
    }

    @Test
    void basicTest() {
        Map<String, Integer> recipe = Map.of(
                "eggs", 1,
                "oil", 300,
                "flour", 1500,
                "sugar", 200,
                "apples", 100);
        Map<String, Integer> available = Map.of(
                "eggs", 5,
                "oil", 21000,
                "flour", 10000,
                "milk", 200,
                "cream", 1000,
                "sugar", 1200,
                "apples", 99);
        Assertions.assertEquals(0, cakes(recipe, available), "For recipe " + recipe + " and ingredients " + available);
    }

    @Test
    void missingIngredient() {
        Map<String, Integer> recipe = Map.of(
                "flour", 500,
                "sugar", 200,
                "eggs", 1,
                "cinnamon", 300);
        Map<String, Integer> available = Map.of(
                "flour", 1200,
                "sugar", 1200,
                "eggs", 5,
                "milk", 200);
        Assertions.assertEquals(0, cakes(recipe, available), "For recipe " + recipe + " and ingredients " + available);
    }


}
