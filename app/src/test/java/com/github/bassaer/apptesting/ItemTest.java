package com.github.bassaer.apptesting;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by nakayama on 2017/10/29.
 */
public class ItemTest {
    private Item mItem;

    @Before
    public void setUp() throws Exception {
        mItem = new Item();
    }

    @Test
    public void nothingToSetParams() throws Exception {
        assertEquals(mItem.getIcon(), 0);
        assertEquals(mItem.getText(), "");
    }

    @Test
    public void shouldReturnSetParam() throws Exception {
        int icon = 100;
        String name = "test";
        mItem = new Item(icon, name);
        assertEquals(mItem.getIcon(), icon);
        assertEquals(mItem.getText(), name);
    }



}