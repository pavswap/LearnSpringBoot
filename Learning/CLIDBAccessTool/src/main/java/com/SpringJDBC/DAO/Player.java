package com.SpringJDBC.DAO;

public class Player {
    private int id;
    private String p_name;
    private int p_rank;

    public Player(int id, String p_name, int p_rank) {
        this.id = id;
        this.p_name = p_name;
        this.p_rank = p_rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_rank() {
        return p_rank;
    }

    public void setP_rank(int p_rank) {
        this.p_rank = p_rank;
    }
}
