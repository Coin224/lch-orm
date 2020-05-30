package com.lch;

import com.lch.orm.ORM;
import org.junit.Test;

public class TestMain {

    private ORM orm = new ORM();


    @Test
    public void t1() {
        orm.save();
    }
}
