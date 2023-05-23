package com.prj.messagepack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.prj.messagepack
 * @Author 彭仁杰
 * @Date 2023/5/13 14:38
 * @Description
 **/
public class Demo {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        MessagePack messagePack = new MessagePack();
        byte[] write = messagePack.write(list);
        List<String> read = messagePack.read(write, Templates.tList(Templates.TString));
        System.out.println(read.get(0) + "-" + read.get(1) + "-" + read.get(2));
    }
}
