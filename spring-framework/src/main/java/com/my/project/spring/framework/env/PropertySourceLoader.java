package com.my.project.spring.framework.env;

import java.io.IOException;
import java.util.List;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 15:38
 **/
public interface PropertySourceLoader {

    String[] getFileExtensions();

    List<MapPropertySource<?>> load(String name) throws IOException;
}
