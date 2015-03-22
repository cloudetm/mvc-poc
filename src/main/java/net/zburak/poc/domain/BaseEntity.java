package net.zburak.poc.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by buraq
 */
public abstract class BaseEntity implements Entity<String> {

    @Id
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
