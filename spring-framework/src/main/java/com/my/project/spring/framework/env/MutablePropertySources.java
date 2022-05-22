package com.my.project.spring.framework.env;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 11:28
 **/
public class MutablePropertySources implements Iterable<MapPropertySource<?>> {

    private final List<MapPropertySource<?>> propertySourceList = new CopyOnWriteArrayList<>();

    public MutablePropertySources() {
    }


    public MutablePropertySources(List<MapPropertySource<?>> propertySourceList) {
        for (MapPropertySource<?> propertySource : propertySourceList){
            addLast(propertySource);
        }
    }

    public void addLast(MapPropertySource<?> propertySource) {
        synchronized (this.propertySourceList){
            removeIfPresent(propertySource);
            this.propertySourceList.add(propertySource);
        }

    }

    public void replace(String name, MapPropertySource<?> propertySource) {
        synchronized (this.propertySourceList) {
            int index = assertPresentAndGetIndex(name);
            this.propertySourceList.set(index, propertySource);
        }
    }

    public void addFirst(MapPropertySource<?> propertySource) {
        synchronized (this.propertySourceList){
            removeIfPresent(propertySource);
            this.propertySourceList.add(0,propertySource);
        }
    }

    public void addAfter(String relativePropertySourceName, MapPropertySource<?> propertySource) {
        assertLegalRelativeAddition(relativePropertySourceName, propertySource);
        synchronized (this.propertySourceList) {
            removeIfPresent(propertySource);
            int index = assertPresentAndGetIndex(relativePropertySourceName);
            addAtIndex(index + 1, propertySource);
        }
    }

    public void addBefore(String relativePropertySourceName,MapPropertySource<?> propertySource){
        assertLegalRelativeAddition(relativePropertySourceName, propertySource);
        synchronized (this.propertySourceList){
            int idnedx = assertPresentAndGetIndex(relativePropertySourceName);
            addAtIndex(idnedx,propertySource);


        }
    }

    private void assertLegalRelativeAddition(String relativePropertySourceName, MapPropertySource<?> propertySource) {
        String newPropertySourceName = propertySource.getName();
        if(relativePropertySourceName.equals(newPropertySourceName)){
            throw new IllegalArgumentException(
                    "PropertySource named '" + newPropertySourceName + "' cannot be added relative to itself");
        }
    }
    private void addAtIndex(int index, MapPropertySource<?> propertySource) {
        removeIfPresent(propertySource);
        this.propertySourceList.add(index, propertySource);
    }

    private int assertPresentAndGetIndex(String name) {
        int index = this.propertySourceList.indexOf(new MapPropertySource(name,new HashMap()));
        if (index == -1) {
            throw new IllegalArgumentException("PropertySource named '" + name + "' does not exist");
        }
        return index;
    }


    private void removeIfPresent(MapPropertySource<?> propertySource) {
        this.propertySourceList.remove(propertySource);
    }


    @Override
    public Iterator<MapPropertySource<?>> iterator() {
        return propertySourceList.iterator();
    }
}
