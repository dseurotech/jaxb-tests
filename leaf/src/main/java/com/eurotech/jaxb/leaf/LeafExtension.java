package com.eurotech.jaxb.leaf;

import com.eurotech.jaxb.node.IntermediateExtension;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "leafExtension")
public class LeafExtension extends IntermediateExtension {
    @XmlElement(name = "leafField")
    public String leafField;

    protected LeafExtension() {
        super();
    }

    public LeafExtension(int httpStatusCode, String errorCode, String message, long intermediateField, String leafField) {
        super(httpStatusCode, errorCode, message, intermediateField);
        this.leafField = leafField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final LeafExtension that = (LeafExtension) o;
        return Objects.equals(leafField, that.leafField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), leafField);
    }

    @Override
    public String toString() {
        return "LeafExtension{" +
                "leafField='" + leafField + '\'' +
                ", intermediateField=" + intermediateField +
                ", httpStatusCode=" + httpStatusCode +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}