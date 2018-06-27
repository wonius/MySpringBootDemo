package com.springboot.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/6/15
 */
public class WordUtil {

    public static final String CUSTOM_DICTIONARY_PATH = "/hanlp/custom/CustomDic.txt";
    public static final String SYNONYM_WORD_PATH = "/hanlp/synonym/SynonDic.txt";

    private static List<Term> list;
    private static Segment segment;
    private static Map synonymMap;

    public static void main(String[] args) {

        refreshDictionary();
        segmentSentence("你是柴犬狗吧");
    }

    /**
     * 刷新用户自定义词典
     */
    public static void refreshDictionary() {
        HanLP.Config.CustomDictionaryPath = new String[] {
                WordUtil.class.getResource(CUSTOM_DICTIONARY_PATH).getPath()
        };

        CustomDictionary.reload();
        segment = HanLP.newSegment().enableCustomDictionary(true);
    }

    /**
     * 分词
     * @param text
     * @return
     */
    public static String segmentSentence(String text) {
        segment = HanLP.newSegment().enableCustomDictionary(true);

        list = segment.seg(text);
        String result = list.get(0).word;
        for(int i =1; i < list.size(); i++) {
            result+=" "+list.get(i).word;
        }
        System.out.println(result);
        return result;
    }

    /**
     * 刷新同义词字典
     */
    public static void refreshSynonym() {
        String path = WordUtil.class.getResource(SYNONYM_WORD_PATH).getPath();
        File file = new File(path);
        BufferedReader reader;
        synonymMap = new HashMap();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word: words) {
                    synonymMap.put(word, words[0]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取同义词
     * @param word
     * @return
     */
    public static String getSynonym(String word) {
        return (String) synonymMap.get(word);
    }

}
