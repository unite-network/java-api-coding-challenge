package eu.unite.address.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressesController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AddressesController(JdbcTemplate jdbcTemplate) throws SQLException {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS addresses (" + "id VARCHAR(255) NOT NULL PRIMARY KEY, "
                + "user_id VARCHAR(255) NOT NULL, "
                + "address_name VARCHAR(255) NOT NULL, "
                + "street VARCHAR(255), "
                + "city VARCHAR(255), "
                + "postal_code VARCHAR(255), "
                + "region VARCHAR(255), "
                + "country VARCHAR(255), "
                + "address_type VARCHAR(255))");
        ScriptUtils.executeSqlScript(
                jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("addresses_data.sql"));
    }

    @RequestMapping("/addresses")
    public @ResponseBody List<AddressesResource> getAddresses() {
        return getAllAddresses();
    }

    @RequestMapping("/{userId}/addresses")
    public @ResponseBody List<AddressesResource> getAddresses(
            @PathVariable("userId") String userId, @RequestParam(value = "type", required = false) String type) {
        List<AddressesResource> userAddresses = new ArrayList<>();
        userAddresses.addAll(getAllAddresses());
        Iterator<AddressesResource> iter = userAddresses.iterator();
        boolean userFound = false;
        while (!userFound) {
            if (iter.hasNext()) {
                if (!iter.next().getUserId().equals(userId)) iter.remove();
            } else {
                userFound = true;
            }
        }

        final List<AddressesResource> finalList = new ArrayList<>();
        if (type == null) {
            finalList.addAll(userAddresses);
            return finalList;
        } else {
            for (AddressesResource address : userAddresses) {
                if (address.getType().name().equals(type)) {
                    finalList.add(address);
                }
            }
        }

        return finalList;
    }

    private List<AddressesResource> getAllAddresses() {
        return jdbcTemplate.query("SELECT * FROM addresses", (rs, rowNum) -> {
            AddressesResource address = new AddressesResource();
            address.setId(rs.getString("id"));
            address.setUserId(rs.getString("user_id"));
            address.setAddressName(rs.getString("address_name"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setPostalCode(rs.getString("postal_code"));
            address.setRegion(rs.getString("region"));
            address.setCountry(rs.getString("country"));
            address.setType(AddressType.valueOf(rs.getString("address_type")));
            return address;
        });
    }
}
