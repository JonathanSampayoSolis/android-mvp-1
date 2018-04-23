package com.example.jjsampayo.mvpsample1.repositories.local;

import com.example.jjsampayo.mvpsample1.beans.God;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class MockedDB {
    public static final ArrayList<God> GOD_LIST = new ArrayList<>();

    public static final List<String> GOD_ROLES = Arrays.asList
            ("ADC", "SUPP", "SOLO", "MID", "JUNGLE");

    public static final List<String> GOD_TYPES = Arrays.asList
            ("MAGICIAN", "HUNTER", "ASSASSIN","WARRIOR", "GUARDIAN");

}
