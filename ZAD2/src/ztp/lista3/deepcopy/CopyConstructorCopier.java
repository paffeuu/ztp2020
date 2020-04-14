package ztp.lista3.deepcopy;

import ztp.lista3.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CopyConstructorCopier implements CountryCopier {
    public List<Country> copyCountryList(List<Country> countryList) {
        List<Country> newCountryList = new ArrayList<>();
        for (Country country : countryList) {
            Country newCountry = new Country(country);
            newCountryList.add(newCountry);
        }
        return newCountryList;
    }
}
