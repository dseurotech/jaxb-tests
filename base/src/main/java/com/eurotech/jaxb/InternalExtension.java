package com.eurotech.jaxb;

//import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "internalExtension")
public class InternalExtension extends BaseInfo {
    @XmlElement(name = "internalField")
    public String internalExtensionField;

    protected InternalExtension() {
        super();
    }

    public InternalExtension(int httpStatusCode, String errorCode, String message, String internalExtensionField) {
        super(httpStatusCode, errorCode, message);
        this.internalExtensionField = internalExtensionField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final InternalExtension that = (InternalExtension) o;
        return Objects.equals(internalExtensionField, that.internalExtensionField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), internalExtensionField);
    }

    @Override
    public String toString() {
        return "InternalExtension{" +
                "internalExtensionField='" + internalExtensionField + '\'' +
                ", httpStatusCode=" + httpStatusCode +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
