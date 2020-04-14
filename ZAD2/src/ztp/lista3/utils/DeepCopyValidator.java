package ztp.lista3.utils;

import ztp.lista3.model.City;
import ztp.lista3.model.Country;
import ztp.lista3.model.Inhabitant;

import java.util.List;

public class DeepCopyValidator {
    public boolean validateDataSet(List<Country> countryList, List<Country> copiedCountryList) {
        if (copiedCountryList.size() != countryList.size()) {
            return false;
        }
        for (int i = 0; i < copiedCountryList.size(); i++) {
            if (!validateCountry(countryList.get(i), copiedCountryList.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean validateCountry(Country country, Country copiedCountry) {
        if (!copiedCountry.getName().equals(country.getName())) {
            return false;
        }
        if (copiedCountry.getCities().size() != country.getCities().size()) {
            return false;
        }
        for (int i = 0; i < copiedCountry.getCities().size(); i++) {
            if (!validateCity(country.getCities().get(i), copiedCountry.getCities().get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean validateCity(City city, City copiedCity) {
        if (!copiedCity.getName().equals(city.getName())) {
            return false;
        }
        if (copiedCity.getInhabitants().size() != city.getInhabitants().size()) {
            return false;
        }
        for (int i = 0; i < copiedCity.getInhabitants().size(); i++) {
            if (!validateInhabitant(city.getInhabitants().get(i), copiedCity.getInhabitants().get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean validateInhabitant(Inhabitant inhabitant, Inhabitant copiedInhabitant) {
        if (!copiedInhabitant.getFirstName().equals(inhabitant.getFirstName())) {
            return false;
        }
        if (!copiedInhabitant.getLastName().equals(inhabitant.getLastName())) {
            return false;
        }
        return true;
    }


}
