package com.bus.ticket.enggine.configure.xendit;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XenditClient {
    @SneakyThrows
    public static Invoice build() {
        Xendit.apiKey = "xnd_development_P4qDfOss0OCpl8RtKrROHjaQYNCk9dN5lSfk+R1l9Wbe+rSiCwZ3jw==";
        try {
            ArrayList<Map<String, Object>> customerAddresses = new ArrayList<>();
            Map<String, Object> customerAddress1 = new HashMap<>();
            customerAddress1.put("city", "Jakarta Selatan");
            customerAddress1.put("state", "Daerah Khusus Ibukota Jakarta");
            customerAddress1.put("country", "Indonesia");
            customerAddress1.put("postal_code", "12345");
            customerAddress1.put("street_line1", "Jalan Makan");
            customerAddress1.put("street_line2", "Kecamatan Kebayoran Baru");
            customerAddresses.add(customerAddress1);

            Map<String, Object> customerAddress2 = new HashMap<>();
            customerAddress2.put("city", "Jakarta Selatan");
            customerAddress2.put("state", "Daerah Khusus Ibukota Jakarta");
            customerAddress2.put("country", "Indonesia");
            customerAddress2.put("postal_code", "12345");
            customerAddress2.put("street_line1", "Jalan Satu");
            customerAddress2.put("street_line2", "Kecamatan Kebayoran Baru");

            Map<String, Object> customerObject = new HashMap<>();
            customerObject.put("given_names", "John");
            customerObject.put("surname", "Doe");
            customerObject.put("email", "johndoe@example.com");
            customerObject.put("mobile_number", "+6287774441111");
            customerObject.put("addresses", customerAddresses);

            Map<String, Object> customerNotificationPreference = new HashMap<>();
            customerNotificationPreference.put("invoice_created", "whatsapp");
            customerNotificationPreference.put("invoice_reminder","whatsapp");
            customerNotificationPreference.put("invoice_paid", "whatsapp");
            customerNotificationPreference.put("invoice_expired", "whatsapp");
            ArrayList<Map<String, Object>> items = new ArrayList<>();
            Map<String, Object> item1 = new HashMap<>();
            item1.put("name", "Testing");
            item1.put("quantity", 1);
            item1.put("price", 100000);
            item1.put("category", "Electronic");
            item1.put("url", "https://yourcompany.com/example_item");
            items.add(item1);
            ArrayList<Map<String, Object>> fees = new ArrayList<>();
            Map<String, Object> fee1 = new HashMap<>();
            fees.add(fee1);
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "demo_1475801962607");
            params.put("amount", 50000);
            params.put("description", "Invoice Demo #123");
            params.put("invoice_duration", 86400);
            params.put("customer", customerObject);
            params.put("customer_notification_preference", customerNotificationPreference);
            params.put("success_redirect_url", "https://www.google.com");
            params.put("failure_redirect_url", "https://www.google.com");
            params.put("currency", "IDR");
            params.put("items", items);
            params.put("fees", fees);
            return Invoice.create(params);
        } catch (XenditException e) {
            throw new XenditException(e.toString());
        }
    }
}