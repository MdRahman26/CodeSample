package rest;

import base.CommonAPI;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class ApiStatusCode extends CommonAPI {
    public static String apiHome = "http://info.venturepulse.org:8080/service-webapp/api/";

    // get all the valid uri
    public List<String> getValidUriList() {
        List<String> uriList = new ArrayList<>();
        uriList.add("AllEmployeeResources");
        uriList.add("LogIn");
        uriList.add("myresource");
        uriList.add("SignUp");
        uriList.add("SingleEmployeeResources");
        return uriList;
    }

    // get five invalid uri
    public List<String> getInvalidUriList() {
        List<String> uriList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String randomUri = RandomStringUtils.randomAlphabetic(12);
            uriList.add(randomUri);
        }
        return uriList;
    }
}
