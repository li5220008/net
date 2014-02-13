package oio;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 上午9:23
 */
public class Employee {
    public String name;
    public int age;
    public static final int LEN=8;

    public Employee(String name, int age) {
        if(name.length()>LEN){
            name=name.substring(0,LEN);
        }else {
            while (name.length()<LEN)
            {
                name+="\u0000";
            }
        }
        this.name = name;
        this.age = age;
    }

}
