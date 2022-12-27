package org.eclipse.jaxb.node;

import org.eclipse.jaxb.BaseInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "intermediateExtension")
public class IntermediateExtension extends BaseInfo {
    @XmlElement(name = "intermediateField")
    public long intermediateField;

    protected IntermediateExtension() {
        super();
    }

    public IntermediateExtension(int httpStatusCode, String errorCode, String message, long intermediateField) {
        super(httpStatusCode, errorCode, message);
        this.intermediateField = intermediateField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final IntermediateExtension that = (IntermediateExtension) o;
        return intermediateField == that.intermediateField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), intermediateField);
    }

    @Override
    public String toString() {
        return "IntermediateExtension{" +
                "intermediateField=" + intermediateField +
                ", httpStatusCode=" + httpStatusCode +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
