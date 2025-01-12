package com.ecommerce.nexus.model;

public enum State {
    // ANDHRA_PRADESH,
    // ARUNACHAL_PRADESH,
    // ASSAM,
    // BIHAR,
    // CHHATTISGARH,
    // GOA,
    // GUJARAT,
    // HARYANA,
    // HIMACHAL_PRADESH,
    // JHARKHAND,
    // KARNATAKA,
    // KERALA,
    // MADHYA_PRADESH,
    // MAHARASHTRA,
    // MANIPUR,
    // MEGHALAYA,
    // MIZORAM,
    // NAGALAND,
    // ODISHA,
    // PUNJAB,
    // RAJASTHAN,
    // SIKKIM,
    // TAMIL_NADU,
    // TELANGANA,
    // TRIPURA,
    // UTTAR_PRADESH,
    // UTTARAKHAND,
    // WEST_BENGAL,

    // ANDAMAN_AND_NICOBAR_ISLANDS,
    // CHANDIGARH,
    // DADRA_AND_NAGAR_HAVELI_AND_DAMAN_AND_DIU,
    // DELHI,
    // JAMMU_AND_KASHMIR,
    // LADAKH,
    // LAKSHADWEEP,
    // PUDUCHERRY;

    ANDHRA_PRADESH("Andhra Pradesh"),
    ARUNACHAL_PRADESH("Arunachal Pradesh"),
    ASSAM("Assam"),
    BIHAR("Bihar"),
    CHHATTISGARH("Chhattisgarh"),
    GOA("Goa"),
    GUJARAT("Gujarat"),
    HARYANA("Haryana"),
    HIMACHAL_PRADESH("Himachal Pradesh"),
    JHARKHAND("Jharkhand"),
    KARNATAKA("Karnataka"),
    KERALA("Kerala"),
    MADHYA_PRADESH("Madhya Pradesh"),
    MAHARASHTRA("Maharashtra"),
    MANIPUR("Manipur"),
    MEGHALAYA("Meghalaya"),
    MIZORAM("Mizoram"),
    NAGALAND("Nagaland"),
    ODISHA("Odisha"),
    PUNJAB("Punjab"),
    RAJASTHAN("Rajasthan"),
    SIKKIM("Sikkim"),
    TAMIL_NADU("Tamil Nadu"),
    TELANGANA("Telangana"),
    TRIPURA("Tripura"),
    UTTAR_PRADESH("Uttar Pradesh"),
    UTTARAKHAND("Uttarakhand"),
    WEST_BENGAL("West Bengal"),

    // Union Territories
    ANDAMAN_AND_NICOBAR_ISLANDS("Andaman and Nicobar Islands"),
    CHANDIGARH("Chandigarh"),
    DADRA_AND_NAGAR_HAVELI_AND_DAMAN_AND_DIU("Dadra and Nagar Haveli and Daman and Diu"),
    DELHI("Delhi"),
    JAMMU_AND_KASHMIR("Jammu and Kashmir"),
    LADAKH("Ladakh"),
    LAKSHADWEEP("Lakshadweep"),
    PUDUCHERRY("Puducherry");

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    State(String displayName) {
        this.displayName = displayName;
    }

}
