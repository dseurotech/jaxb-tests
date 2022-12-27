package org.eclipse.jaxb.tests;

import org.eclipse.jaxb.BaseInfo;

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
