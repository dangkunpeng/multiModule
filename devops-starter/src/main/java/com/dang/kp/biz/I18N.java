package com.dang.kp.biz;

import com.alibaba.fastjson.JSONObject;
import com.dang.kp.biz.demo.KeyVal;
import com.dang.kp.utils.MyUtils;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class I18N {

    public static final String filePath = "/opt/i18n/";
    public static final String fileName_base = "messages.properties";

    public static final List<String> langList = Lists.newArrayList("messages_en_US.properties", "messages_ja_JP.properties", "messages_zh_CN.properties");

    public static void main(String[] args) throws Exception, IOException {

        List<KeyVal> baseList = readFile(fileName_base);
        String group = MyUtils.getKey("properties");
        for (String langName : langList) {
            log.info("当前处理语言文件为{}", langName);
            List<KeyVal> langList = readFile(langName);
            log.info("{}有{}条记录", langName, langList.size());
            List<KeyVal> list = langList.stream().distinct().collect(Collectors.toList());
            log.info("{}去重后有{}条记录", langName, list.size());
            // 语言包按key分组
            Map<String, List<KeyVal>> map = list.stream().collect(Collectors.groupingBy(KeyVal::getKey));
            int counter = 0;
            for (KeyVal keyVal : baseList) {
                if (CollectionUtils.isEmpty(map.get(keyVal.getKey()))) {
                    // 添加到lang
                    list.add(keyVal);
                    counter++;
                }
            }
            log.info("{}合并后有{}条记录,增加了{}条记录", langName, list.size(), counter);
            File file = new File(filePath + group + File.separator + langName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
            list = list.stream().sorted(Comparator.comparing(KeyVal::getKey)) .collect(Collectors.toList());
            StringBuilder sb = new StringBuilder();
            for (KeyVal keyVal : list) {
                String input = keyVal.getKey() + "=" + keyVal.getVal();
                sb.append(keyVal.getKey());
                sb.append("=");
                sb.append(keyVal.getVal());
                sb.append("\n");
            }
            Files.write(sb.toString().getBytes(StandardCharsets.UTF_8), file);
            log.info("{}生成完毕", file.getAbsolutePath());
        }
    }

    public static List<KeyVal> readFile(String fileName) throws Exception {
        List<KeyVal> result = Lists.newArrayList();
        List<String> rows = Files.readLines(new File(filePath + fileName), StandardCharsets.UTF_8);
        for (String row : rows) {
            if (StringUtils.isBlank(row) || StringUtils.startsWith(row, "#") || !StringUtils.contains(row, "=")) {
//                log.info("{}有忽略的行={}", fileName, row);
                continue;
            }
            String[] array = StringUtils.split(row, "=");
            if (array.length != 2) {
//                log.info("{}有无效的行={}", fileName, row);
                continue;
            }
            result.add(KeyVal.builder().key(StringUtils.trim(array[0])).val(StringUtils.trim(array[1])).build());
        }
        return result;
    }
}
