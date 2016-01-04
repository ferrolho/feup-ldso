$("#countryDropdown").change(function () {
    var selectedCountry = $('#countryDropdown').find(":selected").text();

    $("#cityDropdown").prop('disabled', false);

    $.getJSON("/get-country-to-cities", function (json) {
        var cities = json[selectedCountry].sort();

        for (i = 0; i < cities.length; i++) {
            var option = document.createElement("option");
            option.text = cities[i];
            option.value = cities[i];

            $("#cityDropdown").append(option);
        }
    });
});
