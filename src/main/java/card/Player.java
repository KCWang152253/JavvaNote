package card;


import java.util.ArrayList;
import java.util.List;

/**
 * 玩家类
 */
public class Player {
    public String name;
    public List<Card> list=new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }
}

