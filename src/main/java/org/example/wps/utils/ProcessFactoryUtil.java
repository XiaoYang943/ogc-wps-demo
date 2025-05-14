package org.example.wps.utils;

import org.geotools.process.ProcessFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessFactoryUtil {
    public static List<ProcessFactory> loadLocalFactories() {
        List<ProcessFactory> factories = new ArrayList<>();
        try (InputStream is = ProcessFactoryUtil.class.getClassLoader()
                .getResourceAsStream("META-INF/services/org.geotools.process.ProcessFactory")) {

            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty() && !line.startsWith("#")) {
                        Class<?> clazz = Class.forName(line);
                        if (ProcessFactory.class.isAssignableFrom(clazz)) {
                            factories.add((ProcessFactory) clazz.getDeclaredConstructor().newInstance());
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load custom ProcessFactory", e);
        }
        return factories;
    }
}
