package ztp.lista3.deepcopy;

import org.apache.commons.lang3.SerializationUtils;
import ztp.lista3.model.Country;

import java.util.ArrayList;
import java.util.List;

public class ApacheCommonsCopier implements CountryCopier {
    @Override
    public List<Country> copyCountryList(List<Country> countryList) {
        List<Country> newCountryList = new ArrayList<>();
        for (Country country : countryList) {
            Country newCountry = SerializationUtils.clone(country);
            newCountryList.add(newCountry);
        }
        return newCountryList;
    }
}
