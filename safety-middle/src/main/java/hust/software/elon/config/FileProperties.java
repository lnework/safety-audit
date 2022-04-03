package hust.software.elon.config;

import lombok.Setter;

/**
 * @author elon
 * @date 2022/3/30 16:41
 */
@Setter
public class FileProperties {
    public static final int WIN_CODE = 1;
    public static final int MAC_CODE = 2;
    public  Integer systemKind = 0;
    private Integer maxSize;
    private Integer iconSize;
    private String macRoot;
    private String winRoot;
    private String linuxRoot;
    private String other;

    private String userIcon;
    private String audio;

    public String getRoot() {
        switch (systemKind){
            case WIN_CODE:
                return getWinRoot();
            case MAC_CODE:
                return getMacRoot();
            default:
                return getLinuxRoot();
        }
    }


    public Integer getSystemKind() {
        return systemKind;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public Integer getIconSize() {
        return iconSize;
    }

    public String getMacRoot() {
        return macRoot;
    }

    public String getWinRoot() {
        return winRoot;
    }

    public String getLinuxRoot() {
        return linuxRoot;
    }

    public String getUserIcon() {
        return getRoot() + userIcon;
    }

    public String getAudio() {
        return getRoot() + audio;
    }

    public String getOther() {
        return getRoot() + other;
    }
}
