package com.eurotech.jaxb.tests;

import com.eurotech.jaxb.BaseInfo;
import com.eurotech.jaxb.InternalExtension;
import com.eurotech.jaxb.leaf.LeafExtension;
import com.eurotech.jaxb.node.IntermediateExtension;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

public class JaxbTests {

    private Marshaller marshaller;

    private Unmarshaller unmarshaller;

    private final Logger logger = LoggerFactory.getLogger(JaxbTests.class);


    @Before
    public void setUp() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(Wrapper.class, BaseInfo.class, InternalExtension.class, InternalExtension.class, LeafExtension.class);
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
