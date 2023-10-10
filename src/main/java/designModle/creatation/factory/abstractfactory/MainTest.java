package designModle.creatation.factory.abstractfactory;


/**
 * 当产品族只有一个产品时：抽象工厂又退变成工厂方法
 * 抽象出来。
 *      可以抽象成接口（只有方法），可以抽象成抽象类（有些属性也需要用）
 */
public class MainTest {

    public static void main(String[] args) {

        //
        WulinFactory wulinFactory = new WulinWuHanMaskFactory();
        AbstractCar abstractCar = wulinFactory.newCar();

        AbstractMask abstractMask = wulinFactory.newMask();
        abstractMask.protectedMe();


        wulinFactory = new WulinHangZhouMaskFactory();
        AbstractMask abstractMask1 = wulinFactory.newMask();
        abstractMask1.protectedMe();
    }
}
