package tcp;

import sun.plugin2.message.Serializer;

import java.io.Serializable;


/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-18
 * Time: 下午5:06
 */
public class Student implements Serializable {
    private long number;
    private String name;
    private String major;

    public Student(long number, String name, String major) {
        this.number = number;
        this.name = name;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
