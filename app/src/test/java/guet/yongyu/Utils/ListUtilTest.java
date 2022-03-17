package guet.yongyu.Utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilTest {

    @Test
    void replaceFirst() {
        List<String> cmd = new ArrayList<>();
        cmd.add("cmd");
        cmd.add("/k");
        cmd.add("start");
        cmd.add("gcc");
        cmd.add("{sourcefile}");

        System.out.println(cmd);
        String str = "test/main.c";

        ListUtil.replaceFirst(cmd,CompileCommand.sourceFiles,str);

        System.out.println(cmd);
    }

    @Test
    void list2Str(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        System.out.println(ListUtil.list2Str(list));
    }

    @Test
    void strSplit(){
        
        String[] strings = ListUtil.strSplit(PropUtil.getParameter("c"));
        for(String str:strings)
            System.out.println(str);
    }
}