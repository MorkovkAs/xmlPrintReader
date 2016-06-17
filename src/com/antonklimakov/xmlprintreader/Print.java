package com.antonklimakov.xmlprintreader;

/**
 * Created by Anton Klimakov on 17.06.2016.
 */
class Print {
    private String deviceAddress;
    private int Amount;

    String getDeviceAddress() {
        return deviceAddress;
    }

    void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    int getAmount() {
        return Amount;
    }

    void setAmount(int amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "Print:: \tdeviceAddress=" + this.deviceAddress + ", \tAmount=" + this.Amount;
    }
}
