package github.veikkoroc.impl;

import github.veikkoroc.api.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/29 17:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog implements Animal {


    private String name;

    private Integer age;

    @Override
    public String eat() {
        System.out.println(this.name+"大口吃...");
        return null;
    }

    @Override
    public void sleep() {
        System.out.println(this.name+"呼呼大睡...");
    }
}
