package org.agoncal.quarkus.microservices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.test.Mock;


@Mock
@RestClient
public class MockNumberProxy implements NumberProxy {

    @Override
    public IsbnThirteen generateIsbnNumber() {
        IsbnThirteen isbnThirteen = new IsbnThirteen();
        isbnThirteen.isbn13 = "13-mock";
        return isbnThirteen;
    }

    
}
