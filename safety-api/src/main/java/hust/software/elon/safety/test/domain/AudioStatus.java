/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.test.domain;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-04-21")
public enum AudioStatus implements org.apache.thrift.TEnum {
  ONLINE(0),
  OFFLINE(1),
  USER_DELETED(2),
  ADMIN_DELETED(3);

  private final int value;

  private AudioStatus(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static AudioStatus findByValue(int value) { 
    switch (value) {
      case 0:
        return ONLINE;
      case 1:
        return OFFLINE;
      case 2:
        return USER_DELETED;
      case 3:
        return ADMIN_DELETED;
      default:
        return null;
    }
  }
}
