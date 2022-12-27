package org.eclipse.jaxb.tests;


import com.eurotech.jaxb.leaf.LeafExtension;
import org.eclipse.jaxb.BaseInfo;
import org.eclipse.jaxb.InternalExtension;
import org.eclipse.jaxb.node.IntermediateExtension;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JaxbTests {

    private Marshaller marshaller;

    private Unmarshaller unmarshaller;

    private final Logger logger = LoggerFactory.getLogger(JaxbTests.class);

    final Class<?>[] loadClasses(String... packageName) {
        final Reflections reflections = new Reflections(packageName);
        final Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(XmlRootElement.class);
        return annotated.toArray(new Class[annotated.size()]);
    }

    @Before
    public void setUp() throws JAXBException {
//        final JAXBContext jc = JAXBContext.newInstance(
//                Wrapper.class,
//                BaseInfo.class,
//                InternalExtension.class,
//                InternalExtension.class,
//                LeafExtension.class);
        final Map<String, Object> properties = new HashMap<>(1);
        properties.put(MarshallerProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
        final JAXBContext jc = JAXBContextFactory.createContext(
                loadClasses("com.eurotech.jaxb", "org.eclipse"),
                properties);

        logger.info("Using JAXB implementation:\n\t - {}", jc.getClass());
        this.marshaller = jc.createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        this.unmarshaller = jc.createUnmarshaller();
    }

    @Test
    public void canMarshallAndUnmarshallHierarchy() {
        final List<BaseInfo> testCases = Arrays.asList(new BaseInfo(404, "BASE", "Base Info"),
                new InternalExtension(500, "INT", "Internal Ext", "42"),
                new IntermediateExtension(501, "EXT", "External Ext", 42l),
                new LeafExtension(501, "EXT", "External Ext", 42l, "leaf")
        );
        logger.info("******* Testing direct marshalling and unmarshalling of class hierarchy ******");
        testCases
                .stream()
                .forEach(tc -> Assert.assertEquals(tc, backAndForth(tc)));
        logger.info("**********************************************************************************");

        logger.info("********Testing indirect marshalling and unmarshalling of class hierarchy*******");
        testCases
                .stream()
                .forEach(tc -> {
                    Assert.assertEquals(tc, backAndForth(new Wrapper(tc)).baseInfo);
                });
        logger.info("**********************************************************************************");
    }

    private <T> T backAndForth(T info) {
        logger.info("##################################################################################");
        try {
            final StringWriter stringWriter = new StringWriter();
            logger.info("Marshalling and unmarshalling instance:\n => {}", info);
            marshaller.marshal(info, stringWriter);
            logger.info("Marshalled form:\n{}", stringWriter);
            final T unmarshalled = (T) unmarshaller.unmarshal(new StringReader(stringWriter.toString()));
            logger.info("Unmarshalled form:\n <= {}", unmarshalled);
            logger.info("##################################################################################");
            return unmarshalled;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


}
