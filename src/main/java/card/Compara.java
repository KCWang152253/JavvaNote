package card;


import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;

/**
 * 比较规则类  可根据具体比较规则自定义
 */
@Component
public class Compara implements Comparator<Card> {

    private HashMap<String, Integer> soreMap;

    private String color;

    public Compara(CardScoreConfig cardScoreConfig) {
        this.soreMap = cardScoreConfig.getSoreMap();
        this.color=cardScoreConfig.getColor();
    }

    @Override
    public int compare(Card arg0, Card arg1) {


        int result =soreMap.get(arg0.number) - soreMap.get(arg1.number);

        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            int result2 = color.indexOf(arg0.color) - color.indexOf(arg1.color);
            return Integer.compare(result2, 0);
        }
    }
}
