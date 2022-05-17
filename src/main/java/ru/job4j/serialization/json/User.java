package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.java.Contact;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    boolean schoolMan;
    @XmlAttribute
    short growth;
    @XmlAttribute
    String name;
    @XmlElement
    Contact tel;
    @XmlElementWrapper(name = "valueses")
    @XmlElement(name = "values")
    int[] values;

    public User() {
    }

    public User(boolean schoolMan, short growth, String name, Contact tel, int[] values) {
        this.schoolMan = schoolMan;
        this.growth = growth;
        this.name = name;
        this.tel = tel;
        this.values = values;
    }

    public boolean isSchoolMan() {
        return schoolMan;
    }

    public short getGrowth() {
        return growth;
    }

    public String getName() {
        return name;
    }

    public Contact getTel() {
        return tel;
    }

    public void setTel(Contact tel) {
        this.tel = tel;
    }

    public int[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "User{"
                + "schoolMan=" + schoolMan
                + ", growth=" + growth
                + ", name='" + name + '\''
                + ", tel=" + tel
                + ", values=" + Arrays.toString(values)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        User user = new User(
                true,
                (short) 180,
                "Alex",
                new Contact(123456, "+7 (111) 111-11-11"),
                new int[] {1, 2, 3});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user));
        final String userJson =
                "{"
                    + "\"schoolMan\":true,"
                    + "\"growth\":180,"
                    + "\"name\":\"Alex\","
                    + "\"tel\":"
                    + "{"
                        + "\"zipCode\":123456,"
                        + "\"phone\":\"+7 (111) 111-11-11\""
                    + "},"
                    + "\"values\":[1,2,3]"
                + "}";
        final User userMod = gson.fromJson(userJson, User.class);
        System.out.println(userMod);

        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            User rsl = (User) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }

        JSONObject jsonUSer = new JSONObject();
        jsonUSer.put("schoolMan", user.isSchoolMan());
        jsonUSer.put("growth", user.getGrowth());
        jsonUSer.put("name", user.getName());
        jsonUSer.put("tel", new JSONObject(user.getTel()));
        jsonUSer.put("values", new JSONArray(user.getValues()));
        System.out.println(jsonUSer);
        System.out.println(new JSONObject(user));

        User testUs = new User();
        Contact testCon = new Contact();
        testUs.setTel(testCon);
        testCon.setUser(testUs);
        System.out.println(new JSONObject(testUs));
    }
}
