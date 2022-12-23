package com.eurotech.jaxb.tests;

import com.eurotech.jaxb.BaseInfo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "wrapper")
public class Wrapper {
    public Wrapper() {
    }

    public Wrapper(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public BaseInfo baseInfo;
}
