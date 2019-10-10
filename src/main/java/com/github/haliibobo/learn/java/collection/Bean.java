package com.github.haliibobo.learn.java.collection;

import java.util.Objects;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-05-21 11:41
 * @description describe what this class do
 */
public class Bean {

    private String id;
    private String name;

    public Bean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bean bean = (Bean) o;
        return Objects.equals(id, bean.id) &&
            Objects.equals(name, bean.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Bean{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
