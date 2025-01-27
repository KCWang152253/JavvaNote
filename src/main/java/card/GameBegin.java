package card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class GameBegin {

    private static Logger logger = LoggerFactory.getLogger(GameBegin.class);


    @Autowired
    private CardScoreConfig cardScoreConfig;

    @Autowired
    private Compara compara;


    public void playCard() {

        logger.info("\n****************扑克牌游戏开始******************");

        //洗牌
        List<Card> cardList = new GameBegin().createCardsList();


        //玩家4人随机排序
        List<String> playerNameList = cardScoreConfig.getPlayerNameList();
        Collections.shuffle(playerNameList);


        //分牌
        Map<String, Player> playerList = distributeCard(cardList, playerNameList);

        //出牌
        Map<String, List<Card>> result = doPlayCard(playerList, playerNameList);


        //出牌最终结果
        finaResult(result, playerNameList);


    }


    private void finaResult(Map<String, List<Card>> result, List<String> playerNameList) {

        //分组
        ArrayList<String> group1 = new ArrayList<>();
        group1.add(playerNameList.get(0));
        group1.add(playerNameList.get(2));
        ArrayList<String> group2 = new ArrayList<>();
        group2.add(playerNameList.get(1));
        group2.add(playerNameList.get(3));

        HashMap<String, Integer> scoreResultMap = new HashMap<>();
        HashMap<String, Integer> soreMap = cardScoreConfig.getSoreMap();
        for (Map.Entry<String, List<Card>> entry : result.entrySet()) {
            Integer score = entry.getValue().stream().map(card -> soreMap.get(card.number)).reduce(0, Integer::sum);
            scoreResultMap.put(entry.getKey(), score);
        }

        Integer group1Score = scoreResultMap.get(group1.get(0)) + scoreResultMap.get(group1.get(1));
        Integer group2Score = scoreResultMap.get(group2.get(0)) + scoreResultMap.get(group2.get(1));

        logger.info("\n****************随机拿牌顺序：{} *****分组为 小组1成员：{} {} 小组2成员：{} {}*************\n", playerNameList, group1.get(0), group1.get(1), group2.get(0), group2.get(1));


        logger.info("\n****************出牌结束*******\n小组1成员得分：组员：{} 得分：{}  组员：{} 得分：{} ***  小组1总得分：{}  ******\n小组2成员得分：组员：{} 得分：{}  组员：{} 得分：{}  ***** 小组2总得分：{}  ***", group1.get(0), scoreResultMap.get(group1.get(0)), group1.get(1), scoreResultMap.get(group1.get(1)), group1Score, group2.get(0), scoreResultMap.get(group2.get(0)), group2.get(1), scoreResultMap.get(group2.get(1)), group2Score);
        if (group1Score - group2Score > 0) {
            logger.info("\n***********************小组1 获胜***********\n\n");
        } else if (group1Score - group2Score < 0) {
            logger.info("\n***********************小组2 获胜***********\n\n");
        } else {
            logger.info("\n***********************小组2与小组2平局***********\n\n");

        }
    }

    public Map<String, List<Card>> doPlayCard(Map<String, Player> playerList, List<String> playerNameList) {
        logger.info("\n****************出牌开始******************");
        Map<String, List<Card>> result = new HashMap<String, List<Card>>() {{
            put("甲", new ArrayList<>());
            put("乙", new ArrayList<>());
            put("丙", new ArrayList<>());
            put("丁", new ArrayList<>());
        }};
        for (int i = 1; i < 14; i++) {
            logger.info("\n**************** 第{}轮玩家出牌开始******************", i);
            ArrayList<Card> list = new ArrayList<>();
            HashMap<Card, String> tep = new HashMap<>();
            for (String s : playerNameList) {
                Player player = playerList.get(s);
                List<Card> cards = player.list;
                Collections.shuffle(cards);
                Card removed = cards.remove(0);
                logger.info("\n**************** 第{}轮出牌****玩家{}出牌：{}    **************", i, player.name, removed.number + removed.color);
                list.add(removed);
                tep.put(removed, player.name);
            }
            list.sort(compara);
            String name = tep.get(list.get(list.size() - 1));
            result.get(name).addAll(list);
            logger.info("\n**************** 第{}轮玩家出牌结束，玩家{} 获胜   ******************\n\n", i, name);
        }

        return result;
    }

    public Map<String, Player> distributeCard(List<Card> cardList, List<String> playerNameList) {
        logger.info("\n****************分牌开始******************");

        Map<String, Player> playerList = new HashMap<>();
        for (int i = 0; i < playerNameList.size(); i++) {
            String name = playerNameList.get(i);
            logger.info("\n****************玩家：{} 分牌开始******************", name);
            Player player = new Player(name);
            for (int j = 13 * i; j < 13 * (i + 1); j++) {
                player.list.add(cardList.get(j));
            }
            logger.info("\n****************玩家：{} 分牌结束******************", name);
            playerList.put(name, player);

        }
        logger.info("\n****************所有玩家分牌结束******************\n\n");

        return playerList;

    }

    /**
     * 创建扑克牌
     */
    public List<Card> createCardsList() {

        List<Card> cardsList = new ArrayList<>();
        logger.info("\n****************洗牌开始******************");

        String[] color = {"A", "B", "C", "D"};
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String temp : color) {
            for (String number : numbers) {
                Card card = new Card(temp, number);
                cardsList.add(card);
            }
        }
        //添加大小王
        cardsList.add(new Card("", "20K"));
        cardsList.add(new Card("", "20Q"));
        Collections.shuffle(cardsList);

        logger.info("\n****************洗牌结束******************");

        return cardsList;
    }


}
