import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * @Author huilong
 * @create 2020/8/6 16:23
 */
public class testRegex {
    @Test
    public void testRegexMethod() {
        String fileName = "中国.txt";
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String regex = "\\.jpg\\b|\\.png\\b|\\.gif\\b";
        System.out.println(Pattern.matches(regex,fileType));

    }
}
