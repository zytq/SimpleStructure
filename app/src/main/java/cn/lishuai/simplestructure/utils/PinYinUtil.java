package cn.lishuai.simplestructure.utils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {

    public static String getPinYin(String text) {
        {
            StringBuilder stringBuilder = null;
            try {
                HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
                format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
                format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
                stringBuilder = new StringBuilder();
                char[] chars = text.toCharArray();
                for (char ch : chars) {
                    if (Character.isWhitespace(ch)) {
                        continue;
                    }

                    if (ch > 128 || ch < -127) {

                        String[] array = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                        stringBuilder.append(array[0]);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }

            return stringBuilder.toString();
        }
    }
}