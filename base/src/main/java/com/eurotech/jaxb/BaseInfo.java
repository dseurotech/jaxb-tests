package com.eurotech.jaxb;

//import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "base")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseInfo {

    @XmlElement(name = "httpStatusCode")
    public int httpStatusCode;
    @XmlElement(name = "errorCode")
    public String errorCode;
    @XmlElement(name = "message")
    public String message;

    protected BaseInfo() {
    }

    public BaseInfo(int httpStatusCode, String errorCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BaseInfo baseInfo = (BaseInfo) o;
        return httpStatusCode == baseInfo.httpStatusCode && Objects.equals(errorCode, baseInfo.errorCode) && Objects.equals(message, baseInfo.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatusCode, errorCode, message);
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "httpStatusCode=" + httpStatusCode +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
