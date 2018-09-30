package com.lunzi.camry.exe.core.io;

import com.lunzi.camry.exe.util.CharUtil;
import com.lunzi.camry.exe.util.URLUtil;

import java.util.regex.Pattern;

/**
 * Created by lunzi on 2018/9/19 下午8:03
 */
public class FileUtil {
    /** 类Unix路径分隔符 */
    private static final char UNIX_SEPARATOR = CharUtil.SLASH;
    /** Windows路径分隔符 */
    private static final char WINDOWS_SEPARATOR = CharUtil.BACKSLASH;
    /** Windows下文件名中的无效字符 */
    private static Pattern FILE_NAME_INVALID_PATTERN_WIN = Pattern.compile("[\\\\/:*?\"<>|]");

    /** Class文件扩展名 */
    public static final String CLASS_EXT = ".class";
    /** Jar文件扩展名 */
    public static final String JAR_FILE_EXT = ".jar";
    /** 在Jar中的路径jar的扩展名形式 */
    public static final String JAR_PATH_EXT = ".jar!";
    /** 当Path为文件形式时, path会加入一个表示文件的前缀 */
    public static final String PATH_FILE_PRE = URLUtil.FILE_URL_PREFIX;
}
