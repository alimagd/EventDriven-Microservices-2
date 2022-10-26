package com.magd.UserService.projection;

import com.magd.CommonService.model.CardDetails;
import com.magd.CommonService.model.User;
import com.magd.CommonService.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {


    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        //Ideally Get the details from the DB
        CardDetails cardDetails
                = CardDetails.builder()
                .name("Ali Maj")
                .validUntilMonth(2)
                .validUntilYear(2022)
                .cardNumber("290066875")
                .cvv(456)
                .build();

        return User.builder()
                .userId(query.getUserId())
                .firstName("Ali")
                .lastName("Maj")
                .cardDetails(cardDetails)
                .build();
    }

}
