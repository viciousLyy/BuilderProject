package guet.yongyu.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropUtilTest {

    @Test
    void getParameter() {
        String value = PropUtil.getParameter("c");
        System.out.println(value);
    }
}