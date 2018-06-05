package com.lj.autoapp;

/**
 * Created by lulingjie on 2018/5/30.
 */

public class ContactsModel {

    /**
     * id : 1
     * name : 617861546
     * status : 1
     * from_id : 0
     * add_time : 1527497200
     */

    private String id;
    private String name;
    private String status;
    private String from_id;
    private String add_time;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ",name=" + name +
                ",status=" + status +
                ",from_id=" + from_id +
                ",add_time=" + add_time +
                "}";
    }
}
