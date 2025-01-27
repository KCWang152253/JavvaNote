package card;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Configuration
public class CardScoreConfig {

    @Value("${card.score.number_1}")
    private Integer number_1;

    @Value("${card.score.number_2}")
    private Integer number_2;

    @Value("${card.score.number_3}")
    private Integer number_3;

    @Value("${card.score.number_4}")
    private Integer number_4;

    @Value("${card.score.number_5}")
    private Integer number_5;

    @Value("${card.score.number_6}")
    private Integer number_6;

    @Value("${card.score.number_7}")
    private Integer number_7;

    @Value("${card.score.number_8}")
    private Integer number_8;

    @Value("${card.score.number_9}")
    private Integer number_9;

    @Value("${card.score.number_10}")
    private Integer number_10;

    @Value("${card.score.number_j}")
    private Integer number_j;

    @Value("${card.score.number_q}")
    private Integer number_q;

    @Value("${card.score.number_k}")
    private Integer number_k;

    @Value("${card.score.number_20q}")
    private Integer number_20q;

    @Value("${card.score.number_20k}")
    private Integer number_20k;

    @Value("${card.name.array}")
    private String[] list;

    @Value("${card.color}")
    private String color;

    private HashMap<String, Integer> soreMap = new HashMap<>();


    public HashMap<String, Integer> getSoreMap() {
        soreMap.put("1", number_1);
        soreMap.put("2", number_2);
        soreMap.put("3", number_3);
        soreMap.put("4", number_4);
        soreMap.put("5", number_5);
        soreMap.put("6", number_6);
        soreMap.put("7", number_7);
        soreMap.put("8", number_8);
        soreMap.put("9", number_9);
        soreMap.put("10", number_10);
        soreMap.put("J", number_j);
        soreMap.put("Q", number_q);
        soreMap.put("K", number_k);
        soreMap.put("20K", number_20q);
        soreMap.put("20Q", number_20k);
        return soreMap;
    }

    public List<String> getPlayerNameList() {
        return Arrays.asList(list);
    }

    public String getColor() {
        return color;
    }
}
