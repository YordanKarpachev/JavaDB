package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class XmlParser {


    public <T> T fromFile(String path, Class<T> tClass) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

    return (T) unmarshaller.unmarshal(new File(path));
    }
}