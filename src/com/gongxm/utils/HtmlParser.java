package com.gongxm.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import java.io.IOException;

/**
 * Created by gongxm on 2017/7/11.
 */

public class HtmlParser {

    public static String parseToText(String url, String startTag, String endTag) throws IOException {
        Connection con = Jsoup.connect(url);
        con.timeout(30000);
        con.header("User-Agent","ZhuiShuShenQi/3.68.2 (Android 4.4.2; Samsung SC-04E / Samsung GT-I9500; )[preload=false;locale=zh_CN;clientidbase=android-samsung]");
        con.header("X-User-Agent","ZhuiShuShenQi/3.68.2 (Android 4.4.2; Samsung SC-04E / Samsung GT-I9500; )[preload=false;locale=zh_CN;clientidbase=android-samsung]");
        con.header("Content-Type","text/*");
        Document doc = con.get();
        String html = doc.html();
        html = html.split(startTag)[1].split(endTag)[0];
        Document doc2 = Jsoup.parse(html);
        String plainText = getPlainText(doc2);
        return plainText;
    }


    public static String getPlainText(Element element) {
        FormattingVisitor formatter = new FormattingVisitor();
        NodeTraversor traversor = new NodeTraversor(formatter);
        traversor.traverse(element);
        return formatter.toString();
    }

    private static class FormattingVisitor implements NodeVisitor {
        private static final int maxWidth = 80;
        private int width = 0;
        private StringBuilder accum = new StringBuilder();

        public void head(Node node, int depth) {

            String name = node.nodeName();
            if (node instanceof TextNode)
                append(((TextNode) node).text());
            else if (name.equals("li"))
                append("\n * ");
            else if (name.equals("dt"))
                append("  ");
            else if (StringUtil.in(name, "p", "h1", "h2", "h3", "h4", "h5", "tr"))
                append("\n");

        }

        private boolean flag;
        public void tail(Node node, int depth) {
            String name = node.nodeName();
            if (StringUtil.in(name, "br", "dd", "dt", "p", "h1", "h2", "h3", "h4", "h5")) {
                if (!flag) {
                    append("\n");
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }

        private void append(String text) {
            if (text.startsWith("\n"))
                width = 0;
            if (text.equals(" ")
                    && (accum.length() == 0 || StringUtil.in(accum.substring(accum.length() - 1), " ", "\n")))
                return;

            if (text.length() + width > maxWidth) {
                String words[] = text.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    boolean last = i == words.length - 1;
                    if (!last)
                        word = word + " ";
                    if (word.length() + width > maxWidth) {
                        accum.append("\n").append(word);
                        width = word.length();
                    } else {
                        accum.append(word);
                        width += word.length();
                    }
                }
            } else {
                accum.append(text);
                width += text.length();
            }
        }

        @Override
        public String toString() {
            return accum.toString();
        }
    }
}
