package card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class CardRunner implements ApplicationRunner {

    @Autowired
    private GameBegin gameBegin;


    @Override
    public void run(ApplicationArguments args)  {
        //模拟扑克牌游戏
        gameBegin.playCard();
    }
}