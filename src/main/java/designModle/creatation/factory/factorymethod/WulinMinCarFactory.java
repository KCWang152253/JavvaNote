package designModle.creatation.factory.factorymethod;

/**
 * minicar分厂
 */
public class WulinMinCarFactory extends AbstractCarFactory{
    @Override
    public AbstractCar newCar() {
        return new MiniCar();
    }
}
