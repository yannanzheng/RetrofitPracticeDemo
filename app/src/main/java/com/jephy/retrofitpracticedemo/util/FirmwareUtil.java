package com.jephy.retrofitpracticedemo.util;

/**
 * Created by jfyang on 12/28/17.
 */

public class FirmwareUtil {

    /**
     *
     * @param cloudFirmwareVersion 云端版本
     * @param deviceFirmwareVersion 设备版本
     * @return cloudFirmwareVersion > cloudFirmwareVersion?
     */
    public static boolean compareVersion(String cloudFirmwareVersion,String deviceFirmwareVersion){
        try {
            String strClouldVersion = cloudFirmwareVersion.replaceAll("\\.", "");
            String strDeviceVersion = deviceFirmwareVersion.replaceAll("\\.", "");
            int cloudVersion = Integer.parseInt(strClouldVersion);
            int deviceVersion = Integer.parseInt(strDeviceVersion);

            if (cloudVersion / 1000 == 0) {
                cloudVersion *= 10;
            }

            if (deviceVersion / 1000 == 0) {
                deviceVersion *= 10;
            }

            return cloudVersion > deviceVersion;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
