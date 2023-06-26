package designModle;

import lombok.Builder;
import lombok.Data;

/**
 * @author KCWang
 * @version 1.0 构建者模式
 * @date 2023/6/11 下午12:49
 */
@Builder
@Data
public class Person {


    private  Integer age;

    private String sex ;

}
